package com.example.tutoapp

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var listePost: ListView
    var postsArray = ArrayList<Post>()
    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listePost = findViewById(R.id.listeposts)
        postsArray = arrayListOf(
            Post("Bagdad", "Bienvenu sur la facebook d'Ixem", R.drawable.logo),
            Post("Paris", "Bienvenu sur la facebook d'Ixem", R.drawable.logo),
            Post("Tokyo", "Bienvenu sur la facebook d'Ixem", R.drawable.logo),
            Post("Moscow", "Bienvenu sur la facebook d'Ixem", R.drawable.logo),
            Post("Stockholm", "Bienvenu sur la facebook d'Ixem", R.drawable.logo)
        )
        val postsArrayBis = arrayListOf("Post 1", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6")
        adapter = PostAdapter(this, R.layout.item_post, postsArray)
        listePost.adapter = adapter

        listePost.setOnItemClickListener{adapterView, view, position, id ->
            Toast.makeText(this, "Position : $position", Toast.LENGTH_LONG).show()
            val clickedPost = postsArray[position]
            Intent(this, PostDetailsActivity::class.java).also{
                it.putExtra("titre", clickedPost.titre)
                it.putExtra("description", clickedPost.description)
                startActivity(it)
            }
        }

        registerForContextMenu(listePost)
        //val tvHello = findViewById<TextView>(R.id.tvHello)
        //val email = intent.getStringExtra("email")

        // tvHello.text = "Bienvenu(e) : $email"
    }
    override fun onCreateOptionsMenu(menu: Menu?) : Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* if(item.itemId == R.id.itemAdd){
            // Quelque chose
        } else if(item.itemId == R.id.settings){
            // Quelque chose
        } else if(item.itemId == R.id.logout){
            // Quelque chose
        } */

        when(item.itemId){
            R.id.itemAdd -> {
                Toast.makeText(this, "Ajouter un nouveau post",Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "Paramêtre de l'appli",Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.list_context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        val position: Int = info.position
        when(item.itemId){
            R.id.itemShow -> {
                Intent(this, PostDetailsActivity::class.java).also{
                    it.putExtra("titre", postsArray[position].titre)
                    startActivity(it)
                }
            }
            R.id.itemDelete -> {
                postsArray.removeAt(position)
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}