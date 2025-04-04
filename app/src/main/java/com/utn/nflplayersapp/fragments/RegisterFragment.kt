package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.UserDao
import com.utn.nflplayersapp.models.Player
import com.utn.nflplayersapp.models.User

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var v: View
    private lateinit var editNewUsername : EditText
    private lateinit var editNewMail : EditText
    private lateinit var editNewPassword : EditText
    private lateinit var editNewPasswordConf : EditText
    private lateinit var btnSignUp : Button

    private var usersdb : AppDatabase? = null
    private var userDao : UserDao? = null
    lateinit var usersList : MutableList<User>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_register, container, false)

        editNewUsername = v.findViewById(R.id.editNewUsername)
        editNewMail = v.findViewById(R.id.editNewMail)
        editNewPassword = v.findViewById(R.id.editNewPassword)
        editNewPasswordConf = v.findViewById(R.id.editNewPasswordConf)
        btnSignUp = v.findViewById(R.id.btnSignUp)

        return v
    }

    override fun onStart() {
        super.onStart()

        usersdb = AppDatabase.getInstance(v.context)
        userDao = usersdb?.userDao()
        usersList = userDao?.fetchAllUsers() as MutableList<User>

        btnSignUp.setOnClickListener {
            if(editNewUsername.length()>0 && editNewMail.length()>0 && editNewPassword.length()>0 && editNewPassword.text.toString() == editNewPasswordConf.text.toString() ){
                userDao?.insertUser(User(0,editNewUsername.text.toString(),editNewMail.text.toString(),editNewPassword.text.toString()))
                v.findNavController().navigateUp()
            }else{
                Snackbar.make(v,"Check the data", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}