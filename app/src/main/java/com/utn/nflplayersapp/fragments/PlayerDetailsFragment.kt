package com.utn.nflplayersapp.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.PlayerDao
import com.utn.nflplayersapp.models.Player

class PlayerDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerDetailsFragment()
    }

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null

    private lateinit var imgPhotoPlayer : ImageView
    private lateinit var txtNamePlayer : TextView
    private lateinit var txtTeamPlayer : TextView
    private lateinit var txtNumberPlayer : TextView
    private lateinit var btnEdit : Button
    private lateinit var btnDelete : Button

    private val args: PlayerDetailsFragmentArgs by navArgs()

    lateinit var v : View
    lateinit var playerSelected : Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_player_details, container, false)
        //args.playerSelected
        return v
    }



    override fun onStart() {
        super.onStart()

        imgPhotoPlayer = v.findViewById(R.id.imgPhotoPlayer)
        txtNamePlayer = v.findViewById(R.id.txtNamePlayer)
        txtTeamPlayer = v.findViewById(R.id.txtTeamPlayer)
        txtNumberPlayer = v.findViewById(R.id.txtNumberPlayer)
        btnEdit = v.findViewById(R.id.btnEdit)
        btnDelete  = v.findViewById(R.id.btnDelete)

        db = AppDatabase.getInstance(v.context)
        playerDao =db?.playerDao()

        playerSelected = playerDao?.fetchPlayerById(args.id) as Player

        Glide.with(requireContext()).load(playerSelected.photo).circleCrop().into(imgPhotoPlayer)
        txtNamePlayer.text = playerSelected.name
        txtTeamPlayer.text = playerSelected.team
        txtNumberPlayer.text = "#"+playerSelected.number


        btnEdit.setOnClickListener {
            val actionEdit = PlayerDetailsFragmentDirections.actionPlayerDetailsFragmentToEditPlayerFragment(args.id)
            findNavController().navigate(actionEdit)
        }

        btnDelete.setOnClickListener {

            playerDao?.delete(Player(playerSelected.id,"","","",""))

            Snackbar.make(v,"Deleted Player", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()

        }
    }
}