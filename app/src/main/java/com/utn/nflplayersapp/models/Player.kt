package com.utn.nflplayersapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val team: String,
    val number: String,
    val photo: String
) :Parcelable
