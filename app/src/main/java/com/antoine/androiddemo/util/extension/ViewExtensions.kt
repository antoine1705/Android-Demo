package com.antoine.androiddemo.util.extension

import android.view.View

fun View.setVisible(isShow: Boolean) {
    if (isShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}