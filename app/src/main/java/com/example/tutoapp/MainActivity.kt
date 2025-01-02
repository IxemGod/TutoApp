package com.example.tutoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.tutoapp.db.FaceBookDatabase

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var db: FaceBookDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)

        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)
        db = FaceBookDatabase(this)

        val isAuthentificated = sharedPreferences.getBoolean("is_authentificated", false)
        val emailPreference = sharedPreferences.getString("email", null)
        Toast.makeText(this, "Test pas piquer des anetons", Toast.LENGTH_SHORT).show()
        if(isAuthentificated){
            Intent(this, HomeActivity::class.java).also{
                it.putExtra("email", emailPreference)
                startActivity(it)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





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
                //Toast.makeText(this, "Votre email est : ${emailContent}", Toast.LENGTH_SHORT).show()
                //Toast.makeText(this, "Votre password est : ${passwordContent}", Toast.LENGTH_SHORT).show()

            }

            val correctMail = "ixemgod@gmail.com"
            val correctPassword = "azerty"

            if(correctMail.equals(emailContent) && correctPassword.equals(passwordContent)){
                email.setText("")
                password.setText("")

                var intentToHomeActivity : Intent = Intent(this, HomeActivity::class.java)
                intentToHomeActivity.putExtra("email", emailContent)

                startActivity(intentToHomeActivity)

                // Enregistrer dans sharedPreferences le boolean isAuthentificated
                val editor = sharedPreferences.edit()
                editor.putBoolean("is_authentificated", true)
                editor.putString("email", emailContent)
                editor.apply()

            }
            else{
                error.text  = "Mot de passe / Email invalide"
                error.visibility = View.VISIBLE
            }



        })
    }
}