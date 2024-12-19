package com.example.tutoapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val postsArray = arrayListOf(
            Post("Bagdad", "Bienvenu sur la facebook d'Ixem", R.drawable.logo),
            Post("Paris", "Bienvenu sur la facebook d'Ixem", R.drawable.piment),
            Post("Tokyo", "Bienvenu sur la facebook d'Ixem", R.drawable.ordi1),
            Post("Moscow", "Bienvenu sur la facebook d'Ixem", R.drawable.ordi2),
            Post("Stockholm", "Bienvenu sur la facebook d'Ixem", R.drawable.ordi3)
        )
        val postsArrayBis = arrayListOf("Post 1", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6")
        val listePost = findViewById<ListView>(R.id.listeposts)
        val adapter = PostAdapter(this, R.layout.item_post, postsArray)
        listePost.adapter = adapter

        listePost.setOnItemClickListener({adapterView, view, position, id ->
            Toast.makeText(this, "Position : $position", Toast.LENGTH_LONG).show()
            val clickedPost = postsArray[position]
            Intent(this, PostDetailsActivity::class.java).also{
                it.putExtra("titre", clickedPost.titre)
                it.putExtra("description", clickedPost.description)
                startActivity(it)

            }
        })
        //val tvHello = findViewById<TextView>(R.id.tvHello)
        //val email = intent.getStringExtra("email")

        // tvHello.text = "Bienvenu(e) : $email"
    }
}