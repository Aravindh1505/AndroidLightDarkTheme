package com.aravindh.androidlightdarktheme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Base_Night)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
