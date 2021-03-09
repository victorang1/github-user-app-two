package com.example.consumerapp.app.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.consumerapp.R
import com.example.consumerapp.app.MainActivity
import com.example.consumerapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashScreenBinding>(
            this,
            R.layout.activity_splash_screen
        )
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }, 2000)
    }
}