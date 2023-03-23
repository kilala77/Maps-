package com.ragtag.ttub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ragtag.ttub.CreateMonster
import com.ragtag.ttub.CreateNPC
import com.ragtag.ttub.R

class popUpFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 = view.findViewById<Button>(R.id.btn_button1) //Character button
        val btn2 = view.findViewById<Button>(R.id.btn_button2) //Monster button
        val btn3 = view.findViewById<Button>(R.id.btn_button3) //NPC button
        //val editText = view.findViewById<EditText>(R.id.et_edittext)

        btn1.setOnClickListener{
            val CreateTheCharacter = CreateCharacter()
            CreateTheCharacter.show((activity as AppCompatActivity).supportFragmentManager, "Character")
        }
        // to call the button character which will start taking out a image
        btn2.setOnClickListener{
            val CreateTheMonster = CreateMonster()
            CreateTheMonster.show((activity as AppCompatActivity).supportFragmentManager, "Monster")
        }
        // to call the button Monster which will start taking out a image
        btn3.setOnClickListener{
            val CreateTheNPC = CreateNPC()
            CreateTheNPC.show((activity as AppCompatActivity).supportFragmentManager, "NPC")
        }
        // to call the button NPC which will start taking out a image
    }

}