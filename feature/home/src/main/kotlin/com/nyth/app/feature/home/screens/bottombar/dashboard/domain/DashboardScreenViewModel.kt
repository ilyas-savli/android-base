package com.nyth.app.feature.home.screens.bottombar.dashboard.domain


import androidx.lifecycle.viewModelScope
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.model.remote.response.pray.PrayTimeItemUiModel
import com.nyth.app.core.model.remote.response.pray.toUiModel
import com.nyth.app.core.network.BuildConfig
import com.nyth.app.core.network.repository.PrayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val prayRepository: PrayRepository,
    private val sharedPref: SharedPreferenceManager
) : BaseViewModel<DashboardScreenState, DashboardScreenAction>(DashboardScreenState()) {

    init {
        getFormattedDate()
        getPrayTimes()
    }

    fun getPrayTimes() {
        viewModelScope.launch {
            val request = prayRepository.getPrayTimes(
                key = BuildConfig.API_KEY,
                city = sharedPref.selectedCity.takeIf { !it.isNullOrBlank() } ?: "istanbul"
            ).first()

            val baseModel = request.data?.toUiModel()

            updateState(
                state.copy(
                    prayTimes = baseModel,
                )
            )

            val nextTime = getNextPrayerTime(baseModel?.prayTimes)

            nextTime?.let {
                updateState(
                    state.copy(
                        nextPrayTimeName = baseModel?.prayTimes?.find { it.time == nextTime }?.timeName.orEmpty()
                    )
                )
                startCountdown(it)
            }

        }
    }

    fun getFormattedDate() {
        // Miladi tarih (şu an)
        val now = LocalDate.now()
        val miladiFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr", "TR"))
        val formattedMiladi = now.format(miladiFormatter)

        // Miladi → Hicri dönüşüm
        val islamicDate = HijrahDate.from(now)
        val hicriDay = islamicDate.get(ChronoField.DAY_OF_MONTH)
        val hicriMonth = islamicDate.get(ChronoField.MONTH_OF_YEAR) // 1–12
        val hicriYear = islamicDate.get(ChronoField.YEAR)

        val hicriMonthName = listOf(
            "Muharrem", "Safer", "Rebiülevvel", "Rebiülahir",
            "Cemaziyelevvel", "Cemaziyelahir", "Recep", "Şaban",
            "Ramazan", "Şevval", "Zilkade", "Zilhicce"
        )[hicriMonth - 1] // dikkat: 0-index

        val formattedHicri = "$hicriDay $hicriMonthName $hicriYear"

        updateState(
            state.copy(
                date = "$formattedMiladi / $formattedHicri"
            )
        )
    }

    fun startCountdown(targetTime: String) {
        viewModelScope.launch {
            while (true) {
                val now = Calendar.getInstance()
                val format = SimpleDateFormat("HH:mm", Locale("tr", "TR"))
                val target = format.parse(targetTime)

                val targetCal = Calendar.getInstance().apply {
                    time = target!!
                    set(Calendar.YEAR, now.get(Calendar.YEAR))
                    set(Calendar.MONTH, now.get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH))
                }

                if (targetCal.before(now)) {
                    // Yeni güne ait namaz vakti geçtiyse bir gün ekle
                    targetCal.add(Calendar.DAY_OF_MONTH, 1)
                }

                val diffMillis = targetCal.timeInMillis - now.timeInMillis
                val hours = (diffMillis / (1000 * 60 * 60)).toInt()
                val minutes = ((diffMillis / (1000 * 60)) % 60).toInt()
                val seconds = ((diffMillis / 1000) % 60).toInt()

                updateState(
                    state.copy(
                        hours = hours,
                        minutes = minutes,
                        seconds = seconds
                    )
                )

                delay(1000)
            }
        }
    }

    private fun getNextPrayerTime(prayTimes: List<PrayTimeItemUiModel>?): String? {
        if (prayTimes.isNullOrEmpty()) return null

        val now = Calendar.getInstance().time
        val format = SimpleDateFormat("HH:mm", Locale("tr", "TR"))

        for (item in prayTimes) {
            val prayerTime = item.time ?: continue
            try {
                val date = format.parse(prayerTime)
                val calendar = Calendar.getInstance().apply {
                    time = date!!
                    set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR))
                    set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                }

                if (calendar.time.after(now)) {
                    return format.format(calendar.time) // örn: "17:43"
                }

            } catch (_: Exception) {
            }
        }

        return null // tümü geçtiyse
    }

    override fun onReduceState(viewAction: DashboardScreenAction): DashboardScreenState? {
        return null
    }
}
