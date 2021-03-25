package com.antoine.androiddemo.features

import android.os.Bundle
import com.antoine.androiddemo.R
import com.antoine.androiddemo.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}