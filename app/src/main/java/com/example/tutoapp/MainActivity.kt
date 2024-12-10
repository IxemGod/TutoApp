package com.example.tutoapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)


        connect.setOnClickListener(View.OnClickListener { view: View? ->

            val emailContent = email.text.toString()
            val passwordContent = password.text.toString()

            if(emailContent.trim().isEmpty() or passwordContent.trim().isEmpty())
            {
                Toast.makeText(this, "Veuillez remplire tout les champs", Toast.LENGTH_LONG).show()
                error.text  = "Mot de passe / Email invalide"
                error.visibility = View.VISIBLE
            }
            else {
                Toast.makeText(this, "Votre email est : ${emailContent}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Votre password est : ${passwordContent}", Toast.LENGTH_LONG).show()

            }

            val correctMail = "ixemgod@gmail.com"
            val correctPassword = "azerty"

            if(correctMail.equals(emailContent) && correctPassword.equals(passwordContent)){
                Toast.makeText(this, "Vous êtes connecté !", Toast.LENGTH_LONG).show()
            }
            else{
                error.text  = "Mot de passe / Email invalide"
                error.visibility = View.VISIBLE
            }



        })
    }
}