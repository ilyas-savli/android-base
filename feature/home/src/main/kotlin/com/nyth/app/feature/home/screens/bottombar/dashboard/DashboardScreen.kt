package com.nyth.app.feature.home.screens.bottombar.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nyth.app.core.designsystem.theme.LocalCustomColorsPalette
import com.nyth.app.core.model.remote.response.pray.PrayTimeItemUiModel
import com.nyth.app.core.model.remote.response.pray.PrayTimeUiModel
import com.nyth.app.feature.home.screens.bottombar.dashboard.domain.DashboardScreenState
import com.nyth.app.feature.home.screens.bottombar.dashboard.domain.DashboardScreenViewModel

@Composable
fun DashboardScreen(
    navController: NavController
) {
    val viewModel: DashboardScreenViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        viewModel.getPrayTimes()
    }

    DashboardScreen(
        viewModel = viewModel,
        state = state
    )
}

@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel,
    state: DashboardScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                LocalCustomColorsPalette.current.bgGreen
            )
    ) {
        DashboardHeader()
        DashboardDate(
            state.date.orEmpty()
        )
        NextTimeCountDown(
            state.hours,
            state.minutes,
            state.seconds,
            nextPrayTimeName = state.nextPrayTimeName
        )
        PrayTimes(
            prayTimes = state.prayTimes,
            nextPrayTimeName = state.nextPrayTimeName
        )
    }
}

@Composable
fun DashboardHeader() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LocalCustomColorsPalette.current.boxGreen)
            .padding(20.dp),
        text = "Namaz Vakitleri",
        textAlign = TextAlign.Center,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DashboardDate(
    date: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        text = date,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun NextTimeCountDown(
    hours: Int,
    minutes: Int,
    seconds: Int,
    nextPrayTimeName: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(
                            color = LocalCustomColorsPalette.current.boxGreen,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(20.dp),
                    text = hours.toString(),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Saat",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(
                            color = LocalCustomColorsPalette.current.boxGreen,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(20.dp),
                    text = minutes.toString(),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Dakika",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(
                            color = LocalCustomColorsPalette.current.boxGreen,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(20.dp),
                    text = seconds.toString(),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Saniye",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            text = "$nextPrayTimeName Vaktine Kalan Süre",
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }

}

@Composable
fun PrayTimes(
    nextPrayTimeName: String,
    prayTimes: PrayTimeUiModel?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        items(items = prayTimes?.prayTimes ?: emptyList()) {
            PrayTimeItem(
                it,
                nextPrayTimeName
            )
        }
    }
}

@Composable
fun PrayTimeItem(
    prayTime: PrayTimeItemUiModel,
    nextPrayTimeName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = prayTime.timeName.orEmpty(),
            color = if (nextPrayTimeName == prayTime.timeName) Color.Yellow else Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = prayTime.time.orEmpty(),
            color = if (nextPrayTimeName == prayTime.timeName) Color.Yellow else Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}