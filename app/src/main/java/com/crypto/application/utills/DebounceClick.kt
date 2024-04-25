package com.crypto.application.utills

import android.os.SystemClock

private var lastTimeClicked = 0L

fun debounced(debounceTime: Long = 1000L, onClick: () -> Unit) {
    val now = SystemClock.uptimeMillis()
    if (now - lastTimeClicked > debounceTime) onClick()
    lastTimeClicked = now
}
