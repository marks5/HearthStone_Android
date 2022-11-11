package me.gabriel.hearthstone.extension

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.loading(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.GONE
    }
}