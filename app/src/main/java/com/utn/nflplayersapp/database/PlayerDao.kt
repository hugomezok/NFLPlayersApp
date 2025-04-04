package com.utn.nflplayersapp.database

import androidx.room.*
import com.utn.nflplayersapp.models.Player


@Dao
interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY id")
    fun fetchAllPlayers(): MutableList<Player?>?

    @Query("SELECT * FROM players WHERE id = :id")
    fun fetchPlayerById(id: Int): Player?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Update
    fun updatePlayer(player: Player)

    @Delete
    fun delete(player: Player)

    //@Query("SELECT * FROM players ORDER BY id DESC LIMIT 1")
    //@Query("SELECT * FROM players WHERE id = (SELECT MAX(id) FROM players)")
    //fun getLastPlayer()

}