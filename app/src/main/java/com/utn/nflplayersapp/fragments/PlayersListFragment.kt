package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.adapters.PlayersAdapter
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.PlayerDao
import com.utn.nflplayersapp.models.Player

class PlayersListFragment : Fragment() {

    companion object {
        fun newInstance() = PlayersListFragment()
    }

    private lateinit var v: View
    private lateinit var rvPlayers: RecyclerView
    private lateinit var btnAdd : Button

    private var playersList : MutableList<Player> = mutableListOf()

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_players_list, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()

        rvPlayers = v.findViewById(R.id.rvPlayers)
        btnAdd = v.findViewById(R.id.btnAdd)

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()
        playersList = playerDao?.fetchAllPlayers() as MutableList<Player>

        setupRecyclerView()

        btnAdd.setOnClickListener {
            val actionAdd = PlayersListFragmentDirections.actionPlayersListFragmentToAddPlayerFragment()
            findNavController().navigate(actionAdd)
        }

    }


    private fun setupRecyclerView(){

        var playersAdapter = PlayersAdapter(playersList) { position ->
            //Log.d("PlayersListFragment", "Player: ${player.name}")

            val action = PlayersListFragmentDirections.actionPlayersListFragmentToPlayerDetailsFragment(playersList[position].id)
            findNavController().navigate(action)

        }
        // var playersAdapter = PlayersAdapter(playersList)
        //val adapter2 = PlayersAdapter(playersList, ::onPlayerClicked)
        with(rvPlayers){
            adapter = playersAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

}