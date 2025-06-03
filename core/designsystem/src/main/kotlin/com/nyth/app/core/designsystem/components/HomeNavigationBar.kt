package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nyth.app.core.designsystem.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier
            .shadow(elevation = 4.dp),
        title = {
        },
        actions = actions ?: {},
        windowInsets = WindowInsets(top = 0),
        colors = TopAppBarColors(
            containerColor = MaterialTheme.customColorsPalette.white,
            titleContentColor = MaterialTheme.customColorsPalette.secondary100,
            navigationIconContentColor = MaterialTheme.customColorsPalette.secondary300,
            scrolledContainerColor = MaterialTheme.customColorsPalette.secondary300,
            actionIconContentColor = MaterialTheme.customColorsPalette.secondary300,
        )
    )
}

@Composable
@Preview
private fun PreviewComponent() {
    val navController = rememberNavController()
    HomeNavigationBar(navController = navController)
}