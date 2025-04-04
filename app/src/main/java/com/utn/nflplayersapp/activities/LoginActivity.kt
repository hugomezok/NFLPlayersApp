package com.utn.nflplayersapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.models.Preferences


class LoginActivity : AppCompatActivity() {

    companion object{
        lateinit var preferences: Preferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferences = Preferences(applicationContext)

        val theme = PreferenceManager.getDefaultSharedPreferences(this)

        if(theme.getBoolean("theme",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            delegate.applyDayNight()
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            delegate.applyDayNight()
        }
    }


}