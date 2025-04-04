package com.utn.nflplayersapp.fragments

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.activities.LoginActivity
import com.utn.nflplayersapp.activities.LoginActivity.Companion.preferences
import com.utn.nflplayersapp.database.AppDatabase
import com.utn.nflplayersapp.database.UserDao
import com.utn.nflplayersapp.models.User
import java.net.PasswordAuthentication

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var v: View
    private lateinit var editUsername : EditText
    private lateinit var editPassword : EditText
    private lateinit var txtRegister : TextView
    private lateinit var btnLogin : Button
    private lateinit var btnRegister : Button

    private var usersdb : AppDatabase? = null
    private var userDao : UserDao? = null

    lateinit var usersList : MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()

        editUsername = v.findViewById(R.id.editUsername)
        editPassword = v.findViewById(R.id.editPassword)
        btnLogin = v.findViewById(R.id.btnLogin)
        txtRegister = v.findViewById(R.id.txtRegister)
        btnRegister = v.findViewById(R.id.btnRegister)

        usersdb = AppDatabase.getInstance(v.context)
        userDao = usersdb?.userDao()

        btnLogin.setOnClickListener {

            usersList = userDao?.fetchAllUsers() as MutableList<User>
            for( userLogin in usersList){

                if(editUsername.text.toString() == userLogin.name && editPassword.text.toString() == userLogin.password){
                    preferences.saveInt(userLogin.id)
                    val actionLogin = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                    v.findNavController().navigate(actionLogin)
                    activity?.finish()

                }else{
                    Snackbar.make(v,"Incorrect data", Snackbar.LENGTH_SHORT).show()
                }

            }

        }

        btnRegister.setOnClickListener {
            val actionRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            v.findNavController().navigate(actionRegister)
        }


    }


}