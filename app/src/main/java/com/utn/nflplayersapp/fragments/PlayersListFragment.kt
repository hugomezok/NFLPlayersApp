package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.adapters.PlayersAdapter
import com.utn.nflplayersapp.models.Player

class PlayersListFragment : Fragment() {

    companion object {
        fun newInstance() = PlayersListFragment()
    }

    private var playersList : MutableList<Player> = mutableListOf()

    var flag = 0

    private lateinit var v: View
    private lateinit var rvPlayers: RecyclerView
    private lateinit var viewModel: PlayersListViewModel


    playersList.add(Player("Josh Allen", "Buffalo Bills", "17", "https://www.pro-football-reference.com/req/20230307/images/headshots/AlleJo02_2023.jpg"))
    playersList.add(Player("Travis Kelce", "Kansas City Chiefs", "87", "https://www.pro-football-reference.com/req/20230307/images/headshots/KelcTr00_2023.jpg"))
    playersList.add(Player("Justin Jefferson", "Minnesota Vikings", "18", "https://www.pro-football-reference.com/req/20230307/images/headshots/JeffJu00_2022.jpg"))
    playersList.add(Player("T.J. Watt", "Pittsburgh Steelers", "90", "https://www.pro-football-reference.com/req/20230307/images/headshots/WattT.00_2018.jpg"))
    playersList.add(Player("Micah Hyde", "Buffalo Bills", "23", "https://www.pro-football-reference.com/req/20230307/images/headshots/HydeMi00_2023.jpg"))



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_players_list, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

 //       if(flag==0){
  //          playersList.add(Player("Josh Allen", "Buffalo Bills", "17", "https://www.pro-football-reference.com/req/20230307/images/headshots/AlleJo02_2023.jpg"))
   //         playersList.add(Player("Travis Kelce", "Kansas City Chiefs", "87", "https://www.pro-football-reference.com/req/20230307/images/headshots/KelcTr00_2023.jpg"))
     //       playersList.add(Player("Justin Jefferson", "Minnesota Vikings", "18", "https://www.pro-football-reference.com/req/20230307/images/headshots/JeffJu00_2022.jpg"))
       //     playersList.add(Player("T.J. Watt", "Pittsburgh Steelers", "90", "https://www.pro-football-reference.com/req/20230307/images/headshots/WattT.00_2018.jpg"))
         //   playersList.add(Player("Micah Hyde", "Buffalo Bills", "23", "https://www.pro-football-reference.com/req/20230307/images/headshots/HydeMi00_2023.jpg"))
           // flag = 1

   //     }


        rvPlayers = v.findViewById(R.id.rvPlayers)

        setupRecyclerView()

    }

    private fun setupRecyclerView(){
        val playersAdapter = PlayersAdapter(playersList) { player ->
            Log.d("PlayersListFragment", "Player: ${player.name}")

            val action = PlayersListFragmentDirections.actionPlayersListFragmentToPlayerDetailsFragment(player)
            findNavController().navigate(action)

        }

        val adapter2 = PlayersAdapter(playersList, ::onPlayerClicked)

        with(rvPlayers){
            adapter = playersAdapter
            layoutManager = LinearLayoutManager(context)
        }

        //rvPlayers.adapter = adapter
        //rvPlayers.layoutManager = LinearLayoutManager(context)
    }

    private fun onPlayerClicked(player: Player){
        Log.d("PlayersListFragment", "Player: ${player.name}")
    }
}