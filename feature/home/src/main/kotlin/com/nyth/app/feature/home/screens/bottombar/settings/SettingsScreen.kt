package com.nyth.app.feature.home.screens.bottombar.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nyth.app.core.designsystem.theme.LocalCustomColorsPalette
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.model.local.Screen
import com.nyth.app.feature.home.screens.bottombar.settings.domain.SettingsScreenState
import com.nyth.app.feature.home.screens.bottombar.settings.domain.SettingsScreenViewModel

@Composable
fun SettingsScreen(onBack: () -> Unit = {}, navToNext : (Screen) -> Unit = {}) {
    val viewModel: SettingsScreenViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    SettingsScreen(
        state = state,
        onChangeSelectedCity = { selectedCity ->
            viewModel.changeSelectedCity(selectedCity = selectedCity)
        },
        onBack = onBack,
        navToNext = navToNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    state: SettingsScreenState,
    onChangeSelectedCity: (String) -> Unit = {},
    onBack: () -> Unit = {},
    navToNext: (Screen) -> Unit = {}
) {
    val context = LocalContext.current
    val trCities = remember {
        context.resources.getStringArray(com.nyth.app.core.designsystem.R.array.turkey_cities)
            .toList()
    }
    val showCitiesSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    LocalCustomColorsPalette.current.bgGreen
                )
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            onBack()
                        },
                    painter = painterResource(com.nyth.app.core.designsystem.R.drawable.ic_arrow_back_ios),
                    contentDescription = null,
                    tint = MaterialTheme.customColorsPalette.white
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(com.nyth.app.core.designsystem.R.string.settings),
                    color = LocalCustomColorsPalette.current.white,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(com.nyth.app.core.designsystem.R.string.support),
                color = LocalCustomColorsPalette.current.white,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SubItem(
                title = stringResource(com.nyth.app.core.designsystem.R.string.support),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))
            SubItem(
                title = stringResource(com.nyth.app.core.designsystem.R.string.privacy_policy),
                onClick = {
                    navToNext(Screen.PrivacyPolicy)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(com.nyth.app.core.designsystem.R.string.location),
                color = LocalCustomColorsPalette.current.white,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            SubItem(
                title = state.selectedCity.orEmpty(),
                onClick = {
                    showCitiesSheet.value = true
                }
            )
        }

        if (showCitiesSheet.value) {
            ModalBottomSheet(
                onDismissRequest = { showCitiesSheet.value = false },
                sheetState = sheetState
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp) // optional height
                ) {
                    items(trCities) { city ->
                        Text(
                            text = city,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    onChangeSelectedCity(city)
                                    showCitiesSheet.value = false
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = LocalCustomColorsPalette.current.white,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(com.nyth.app.core.designsystem.R.drawable.ic_arrow_forward_ios),
            contentDescription = null,
            tint = MaterialTheme.customColorsPalette.white
        )
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    val state = SettingsScreenState()
    SettingsScreen(state = state)
}