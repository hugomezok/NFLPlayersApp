package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.PlayerDao
import com.utn.nflplayersapp.models.Player

class EditPlayerFragment : Fragment() {

    companion object {
        fun newInstance() = EditPlayerFragment()
    }

    private lateinit var v : View
    private lateinit var btnUpdate : Button
    private lateinit var editPhotoPlayer : EditText
    private lateinit var editNamePlayer : EditText
    private lateinit var editTeamPlayer : EditText
    private lateinit var editNumberPlayer : EditText

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null

    private val args: PlayerDetailsFragmentArgs by navArgs()

    lateinit var playerSelected : Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_edit_player, container, false)
        btnUpdate = v.findViewById(R.id.btnUpdate)
        editPhotoPlayer = v.findViewById(R.id.editPhotoPlayer)
        editNamePlayer = v.findViewById(R.id.editNamePlayer)
        editTeamPlayer = v.findViewById(R.id.editTeamPlayer)
        editNumberPlayer = v.findViewById(R.id.editNumberPlayer)
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        playerSelected = playerDao?.fetchPlayerById(args.id) as Player

        editPhotoPlayer.setText(playerSelected.photo)
        editNamePlayer.setText(playerSelected.name)
        editTeamPlayer.setText(playerSelected.team)
        editNumberPlayer.setText(playerSelected.number)


        btnUpdate.setOnClickListener {


            playerDao?.updatePlayer(
                Player(
                    playerSelected.id,
                    editNamePlayer.text.toString(),
                    editTeamPlayer.text.toString(),
                    editNumberPlayer.text.toString(),
                    editPhotoPlayer.text.toString()
            )
            )


            Snackbar.make(v,"Updated Player", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()

        }
    }

}