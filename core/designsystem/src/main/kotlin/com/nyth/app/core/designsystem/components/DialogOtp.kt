package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.CustomColorsPalette
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.enums.MessageType
import com.nyth.app.core.model.local.enums.UiState

@Composable
fun DialogOtp(
    onConfirm: (String, String?) -> Unit,
    onDismiss: (() -> Unit)?,
    onResend: ((String?) -> Unit)?,
    message: String? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    uiState: UiState = UiState.SUCCESS,
    otpTimeSecond: Int = 120,
    secretInfo: String
) {
    val sendAgain = remember {
        mutableStateOf(false)
    }
    val isOtpFilled = remember {
        mutableStateOf(false)
    }
    val otpCode = remember {
        mutableStateOf(String.empty)
    }

    val errorMessage:@Composable BoxScope.() -> Unit = {
        message?.let { message ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp)
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                CustomSnackBar(
                    message = message,
                    messageType =
                    if (uiState == UiState.SUCCESS)
                        MessageType.SUCCESS
                    else
                        MessageType.ERROR
                )
            }
        }
    }
    val fullText = stringResource(id = R.string.otp_description, secretInfo)
    val startIndex = fullText.indexOf(secretInfo)
    val endIndex = startIndex + secretInfo.length
    val otpInfoText = AnnotatedString.Builder().apply {
        append(fullText)
        addStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontWeight = FontWeight(400),
                color = CustomColorsPalette().secondary500
            ),
            start = startIndex,
            end = endIndex
        )
    }.toAnnotatedString()
    val localFocusManager = LocalFocusManager.current
    val maxDialogHeight = remember { mutableStateOf(0.dp) }
    TrickyHeight(onHeightChanged = { maxDialogHeight.value = it })

    Dialog(
        onDismissRequest = { onDismiss?.invoke() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxDialogHeight.value)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(24.dp)
                    .background(
                        color = MaterialTheme.customColorsPalette.white,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(27.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 27.dp),
                            text = stringResource(id = R.string.confirmation_code),
                            style = typographyNunito.mediumSecondary300S20L30
                        )
                        Icon(
                            modifier = Modifier
                                .padding(end = 27.dp)
                                .clickable { onDismiss?.invoke() },
                            painter = painterResource(id = R.drawable.ic_circle_close),
                            contentDescription = String.empty
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.secondary200
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(start = 27.dp, end = 27.dp),
                        text = otpInfoText,
                        style = typographyNunito.regularNeutral500S12H30
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OTPTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        value = otpCode.value,
                        length = 6
                    ) {
                        otpCode.value = it
                        isOtpFilled.value = it.length == 6
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomButton(
                        modifier = Modifier
                            .padding(start = 23.dp, end = 24.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.confirm),
                        containerColor = MaterialTheme.customColorsPalette.primary100,
                        shape = RoundedCornerShape(4.dp),
                        enabled = isOtpFilled.value && ! sendAgain.value,
                        onClick = {
                            onConfirm.invoke(otpCode.value, secretInfo)
                        }
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    if (sendAgain.value) {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    sendAgain.value = false
                                    onResend?.invoke(secretInfo)
                                },
                            text = stringResource(id = R.string.send_again),
                            style = typographyNunito.regularNeutral500S12H30.copy(textDecoration = TextDecoration.Underline)
                        )
                    } else {
                        CounterComponent(
                            startNumber = otpTimeSecond,
                            numberStyle = typographyNunito.regularNeutral500S12H30,
                            onCountFinished = { sendAgain.value = true }
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
                DefaultLoadingProgress(
                    modifier = Modifier
                        .matchParentSize()
                        .background(color = MaterialTheme.customColorsPalette.white),
                    uiState = uiState
                )
            }

            errorMessage()
        }
    }
}

@Composable
private fun TrickyHeight(
    onHeightChanged: (Dp) -> Unit,
) {
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .onSizeChanged {
                onHeightChanged.invoke(with(density) { it.height.toDp() })
            }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDialog() {
    Column {
        Spacer(modifier = Modifier.height(180.dp))
        DialogOtp(
            onConfirm = { _ , _ -> },
            onDismiss = {},
            onResend = {},
            secretInfo = "+90 555 555 55 54",
        )
    }
}