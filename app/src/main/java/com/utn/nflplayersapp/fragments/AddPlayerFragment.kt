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
import com.google.android.material.snackbar.Snackbar
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.PlayerDao
import com.utn.nflplayersapp.models.Player

class AddPlayerFragment : Fragment() {

    companion object {
        fun newInstance() = AddPlayerFragment()
    }

    private lateinit var v: View
    private lateinit var btnAdd : Button
    private lateinit var editPhotoPlayer : EditText
    private lateinit var editNamePlayer : EditText
    private lateinit var editTeamPlayer : EditText
    private lateinit var editNumberPlayer : EditText

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_add_player, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd = v.findViewById(R.id.btnAdd)
        editPhotoPlayer = v.findViewById(R.id.editPhotoPlayer)
        editNamePlayer = v.findViewById(R.id.editNamePlayer)
        editTeamPlayer = v.findViewById(R.id.editTeamPlayer)
        editNumberPlayer = v.findViewById(R.id.editNumberPlayer)

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        btnAdd.setOnClickListener {

            if(editNamePlayer.length()>0 && editNumberPlayer.length()>0 && editTeamPlayer.length()>0 && editPhotoPlayer.length()>0){
                playerDao?.insertPlayer(Player(0,editNamePlayer.text.toString(),editTeamPlayer.text.toString(),editNumberPlayer.text.toString(),editPhotoPlayer.text.toString()))
                Snackbar.make(v, "Added Player", Snackbar.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            else{
                Snackbar.make(v, "Complete the data", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

}