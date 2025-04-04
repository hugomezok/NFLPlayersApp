package com.utn.nflplayersapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.activities.LoginActivity
import com.utn.nflplayersapp.activities.MainActivity
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.UserDao
import com.utn.nflplayersapp.models.User

class UserFragment : Fragment() {

    lateinit var v: View

    lateinit var txtUsername : TextView
    lateinit var btnSettings : Button
    lateinit var btnLogout : Button
    lateinit var userLog : User

    private var usersdb : AppDatabase? = null
    private var userDao : UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user, container, false)

        txtUsername = v.findViewById(R.id.txtUsername)
        btnSettings = v.findViewById(R.id.btnSettings)
        btnLogout = v.findViewById(R.id.btnLogout)

        return v
    }

    override fun onStart() {
        super.onStart()

        usersdb = AppDatabase.getInstance(v.context)
        userDao = usersdb?.userDao()

        var idUser = LoginActivity.preferences.getInt()
        userLog = userDao?.fetchUserById(idUser) as User

        txtUsername.text = userLog.name

        btnSettings.setOnClickListener {
            val action = UserFragmentDirections.actionUserFragmentToSettingsActivity()
            v.findNavController().navigate(action)
        }

        btnLogout.setOnClickListener {

            val showPopUp = PopUpFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }
    }

}