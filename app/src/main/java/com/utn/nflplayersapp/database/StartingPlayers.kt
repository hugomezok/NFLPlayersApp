package com.utn.nflplayersapp.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.models.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingPlayers (private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingPlayers", "Pre-populating database...")
            fillWithStartingPlayers(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingPlayers(context: Context) {
        val players = listOf(
            Player(0,"Josh Allen", "Buffalo Bills", "17", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/ghmmndgjefpxziks7ntf"),
            Player(0,"Travis Kelce", "Kansas City Chiefs", "87", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/ryzr3vbragwe50vtj9af"),
            Player(0,"Justin Jefferson", "Minnesota Vikings", "18", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/zdstmm4sloqnichybgja"),
            Player(0,"T.J. Watt", "Pittsburgh Steelers", "90", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/yuqxfzlqjuz7yho5fdpg"),
            Player(0,"Micah Hyde", "Buffalo Bills", "23", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/xxdwo2ndjc4qm9q5lpgx"),
            Player(0,"Patrick Mahomes", "Kansas City Chiefs", "15", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/oagzjdsdoezmp6frggrn"),
            Player(0,"Stefon Diggs", "Buffalo Bills", "14", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/zf6nbnzcfzu4y7yq2nzk"),
            Player(0,"Maxx Crosby", "Oakland Raiders", "98", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/ryx2ur9vl647z34ltt2q"),
            Player(0,"Tyler Bass", "Buffalo Bills", "2", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/dhzendyrtdfkenakaubb"),
            Player(0,"Joe Burrow", "Cincinnati Bengals", "9", "https://static.www.nfl.com/image/private/t_player_profile_landscape/f_auto/league/jzzqz5ubkilrn9dpxtlp")

        )

        val dao = AppDatabase.getInstance(context)?.playerDao()

        players.forEach {
            dao?.insertPlayer(it)
        }
    }

}

