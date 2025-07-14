package com.defacto

import android.util.LayoutDirection
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.core.text.layoutDirection
import com.defacto.constants.UIConstants.BOTTOM_BAR_HEIGHT
import com.defacto.constants.UIConstants.DEFAULT_PAGE_ANIMATION_DURATION
import com.defacto.enums.VisibilityStateEnum
import com.defacto.extensions.safeGetFalse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import java.util.Locale

@Composable
@Stable
fun Modifier.baseScreen(
    isFullScreen: Boolean = false, backgroundColor: Color = Color.White
): Modifier {
    val bottomBarInset =
        remember { WindowInsets(bottom = if (isFullScreen) 0.dp else BOTTOM_BAR_HEIGHT.dp) }
    val insets = WindowInsets.systemBars.add(bottomBarInset)
    return this
        .windowInsetsPadding(insets.union(WindowInsets.ime))
        .fillMaxSize()
        .background(backgroundColor)
        .safeClickable { } // prevent click through
}

@Stable
fun Modifier.rtl(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.RTL) this.scale(
        scaleX = -1f, scaleY = 1f
    )
    else this
}

@Composable
fun Modifier.conditional(
    condition: Boolean, modifier: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

@Composable
fun Modifier.isVisibleAlsoDo(
    preCheck: (() -> Boolean)? = null, doAfter: (suspend () -> Unit)? = null
): Modifier {
    val mutex = Mutex()
    val coroutineScope = rememberCoroutineScope()

    return onGloballyPositioned { layoutCoordinates -> // Create a mutex at the appropriate scope (e.g., class level)
        coroutineScope.launch(Dispatchers.IO) {
            mutex.withLock {
                try {
                    if (!preCheck?.invoke().safeGetFalse()) return@withLock

                    val isVisible = layoutCoordinates.parentLayoutCoordinates?.let {
                        val parentBounds = it.boundsInWindow()
                        val childBounds = layoutCoordinates.boundsInWindow()
                        parentBounds.overlaps(childBounds)
                    }.safeGetFalse()

                    if (!isVisible) return@withLock

                    doAfter?.invoke()
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }
}

@Composable
fun Modifier.safeClickable(
    debounceTimeMillis: Long = DEFAULT_PAGE_ANIMATION_DURATION.toLong(), onClick: () -> Unit
): Modifier = composed {
    var lastClickTimeMillis by remember { mutableLongStateOf(0L) }

    clickable {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTimeMillis) >= debounceTimeMillis) {
            onClick()
            lastClickTimeMillis = currentTime
        }
    }
}

@Composable
fun Modifier.isVisibleOnScreen(
    isVisible: (VisibilityStateEnum) -> Unit = { }
): Modifier {
    val mutex = Mutex()
    val coroutineScope = rememberCoroutineScope()

    return onGloballyPositioned { layoutCoordinates ->
        coroutineScope.launch(Dispatchers.IO) {
            mutex.withLock {
                try {
                    val visible = layoutCoordinates.parentLayoutCoordinates?.let {
                        val childBounds = layoutCoordinates.boundsInWindow()
                        val visibleHeight = childBounds.height
                        val itemHeight = layoutCoordinates.size.height
                        when {
                            visibleHeight >= itemHeight -> VisibilityStateEnum.VISIBLE
                            (visibleHeight > 0 && visibleHeight < itemHeight) -> VisibilityStateEnum.PARTIALLY_VISIBLE
                            else -> VisibilityStateEnum.GONE
                        }
                    } ?: run {
                        VisibilityStateEnum.GONE
                    }

                    isVisible.invoke(visible)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }
}