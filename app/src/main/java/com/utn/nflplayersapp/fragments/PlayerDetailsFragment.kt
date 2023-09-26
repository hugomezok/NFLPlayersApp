package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.utn.nflplayersapp.R

class PlayerDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerDetailsFragment()
    }

    private lateinit var viewModel: PlayerDetailsViewModel
    private val args: PlayerDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args.player
        return inflater.inflate(R.layout.fragment_player_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayerDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}