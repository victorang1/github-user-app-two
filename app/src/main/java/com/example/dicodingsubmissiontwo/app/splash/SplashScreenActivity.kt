package com.example.dicodingsubmissiontwo.app.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.app.MainActivity
import com.example.dicodingsubmissiontwo.databinding.ActivitySplashScreenBinding

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