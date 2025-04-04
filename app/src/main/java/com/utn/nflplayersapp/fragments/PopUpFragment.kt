package com.utn.nflplayersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.utn.nflplayersapp.R

class PopUpFragment : DialogFragment() {

    companion object {
        fun newInstance() = PopUpFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pop_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnYes = view.findViewById<Button>(R.id.btnYes)
        val btnNo = view.findViewById<Button>(R.id.btnNo)
/*
        val txtNameApp = view.findViewById<TextView>(R.id.txtNameApp)
        val txtMessage = view.findViewById<TextView>(R.id.txtMessage)
*/
        btnYes.setOnClickListener {
            activity?.finish()
        }
        btnNo.setOnClickListener {
            dismiss()
        }

    }

}