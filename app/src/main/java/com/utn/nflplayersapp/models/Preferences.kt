package com.utn.nflplayersapp.models

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context: Context){

    val SHARED_NAME = "Username"
    val SHARED_ID = "id"

    val sharedPref = context.getSharedPreferences(SHARED_NAME, 0)

    fun getName():String{
        return sharedPref.getString(SHARED_NAME, "")!!
    }

    fun getInt():Int{
        return sharedPref.getInt(SHARED_ID,0)
    }

    fun saveInt(id:Int){
        sharedPref.edit().putInt(SHARED_ID,id).apply()
    }

}