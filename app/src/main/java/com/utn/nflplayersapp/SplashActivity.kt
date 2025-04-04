package com.utn.nflplayersapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.utn.nflplayersapp.activities.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000 // 2 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Handler().postDelayed
        Handler(Looper.getMainLooper()).postDelayed(

            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            , SPLASH_TIME_OUT)

    }
}