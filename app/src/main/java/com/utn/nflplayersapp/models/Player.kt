package com.utn.nflplayersapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "players")
@Parcelize
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "team")
    var team: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "url-photo")
    var photo: String


) :Parcelable
