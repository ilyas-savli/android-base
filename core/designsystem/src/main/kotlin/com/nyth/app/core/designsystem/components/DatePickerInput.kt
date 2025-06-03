package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.ext.DateUtils
import com.nyth.app.core.model.ext.StringExt.empty
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
data class PastOrPresentSelectableDates(var minSeconds: Int, var maxSeconds: Int) :
    SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val minDateMillis = Calendar.getInstance().apply {
            add(Calendar.SECOND, minSeconds)
        }.timeInMillis

        val maxDateMillis = Calendar.getInstance().apply {
            add(Calendar.SECOND, maxSeconds)
        }.timeInMillis

        return utcTimeMillis in minDateMillis..maxDateMillis
    }
}

@OptIn(ExperimentalMaterial3Api::class)
data class PastOrPresentSelectableDatesWithLong(var min: Long? = null, var max: Long? = null) :
    SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        if (min != null && max != null) {
            return utcTimeMillis in min!!..max!!
        }

        if (min != null) {
            return utcTimeMillis >= min!!
        }

        if (max != null) {
            return utcTimeMillis <= max!!
        }

        return true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
class FutureSelectableDates : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis

        return utcTimeMillis >= today
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerInput(
    modifier: Modifier = Modifier,
    title: String? = null,
    annotatedTitle: AnnotatedString? = null,
    placeholder: String = String.empty,
    selectedDate: Date?,
    presentDateFormat: String = "dd/MM/yyyy",
    dataFormat: String = "yyyy-MM-dd",
    checkField: MutableState<Boolean>? = mutableStateOf(false),
    required: Boolean = false,
    minSeconds: Int? = null,
    maxSeconds: Int? = null,
    minLong: Long? = null,
    maxLong: Long? = null,
    onUpdate: (String?) -> Unit,
    onlyFutureSelectable: Boolean = false,
    enabled: Boolean = true
) {
    val dialogShow = remember { mutableStateOf(false) }
    val selectableDates = if (minSeconds != null && maxSeconds != null) {
        PastOrPresentSelectableDates(
            minSeconds = minSeconds,
            maxSeconds = maxSeconds
        )

    } else if (onlyFutureSelectable) {
        FutureSelectableDates()
    } else {
        PastOrPresentSelectableDatesWithLong(
            min = minLong,
            max = maxLong
        )
    }

    val showError =
        required.safeGet() && checkField?.value.safeGet() && selectedDate == null
    val showErrorContainer: @Composable (Boolean) -> Unit = {
        if (it) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_info),
                    tint = MaterialTheme.customColorsPalette.error,
                    contentDescription = String.empty
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = stringResource(id = R.string.required_field),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.error
                    )
                )
            }
        }
    }

    val dateToString =
        selectedDate?.time?.let {
            DateUtils.convertMillisToDateString(
                it,
                format = presentDateFormat
            )
        }
            ?: String.empty

    if (dialogShow.value) {
        val dateState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate?.time,
            selectableDates = selectableDates
        )

        val isFirst: MutableState<Boolean> = remember { mutableStateOf(true) }

        LaunchedEffect(key1 = dateState.selectedDateMillis) {
            val date = dateState.selectedDateMillis?.let {
                DateUtils.convertMillisToDateString(
                    it,
                    dataFormat
                )
            }

            if (!isFirst.value) {
                onUpdate(date)
                dialogShow.value = false
            }
            isFirst.value = false
        }
        Dialog(
            onDismissRequest = { dialogShow.value = false }, properties = DialogProperties(
                usePlatformDefaultWidth = false, dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = MaterialTheme.customColorsPalette.white)
            ) {
                DatePicker(
                    state = dateState
                )
            }
        }
    }
    Column(
        modifier = modifier
    ) {
        if (annotatedTitle != null) {
            Text(
                text = annotatedTitle, style = typographyNunito.mediumNeutral800S14L21
            )
        } else if (title != null) {
            Text(
                text = title, style = typographyNunito.mediumNeutral800S14L21
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (enabled) {
                    dialogShow.value = true
                }
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.customColorsPalette.secondary300
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                    text = if (dateToString == String.empty) placeholder else dateToString,
                    style = if (dateToString == String.empty) typographyNunito.regularNeutral500S14H17 else typographyNunito.mediumNeutral800S14L21,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            showErrorContainer(showError)
        }
    }
}