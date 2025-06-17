package com.nyth.app.feature.home.screens.bottombar.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.feature.home.screens.bottombar.search.domain.SearchScreenViewModel

@Composable
fun SearchScreenRoute(
    onBack: () -> Unit, navToNext: (Screen) -> Unit
) {
    val viewModel: SearchScreenViewModel = hiltViewModel()

    SearchScreen(
        onBack = onBack, navToNext = navToNext
    )
}

@Composable
private fun SearchScreen(
    onBack: () -> Unit, navToNext: (Screen) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Search")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    SearchScreen(onBack = {}, navToNext = {})
}