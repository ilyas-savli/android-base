package com.nyth.app.feature.home.screens.privacy

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.nyth.app.core.designsystem.theme.LocalCustomColorsPalette
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit = {}) {
    PrivacyPolicyScreen(
        onBack = onBack, state = ""
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun PrivacyPolicyScreen(
    state: String,
    onBack: () -> Unit = {}
) {
    var webview: WebView? = remember { null }
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Toolbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.customColorsPalette.boxGreen)
                .padding(16.dp),
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
                text = stringResource(com.nyth.app.core.designsystem.R.string.privacy_policy),
                color = LocalCustomColorsPalette.current.white,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
            webview = WebView(context).apply {
                webViewClient = WebViewClient() // Ensures URLs open inside the WebView
                settings.javaScriptEnabled = true // Optional: enable if the site uses JS
                loadUrl("https://ilyas-savli.github.io/apps-privacy-policy/prayApp/index.html")
            }
            webview!!
        }, update = {
            webview?.apply {
                settings.javaScriptEnabled = true // Optional: enable if the site uses JS
            }
        })
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    PrivacyPolicyScreen(state = "")
}