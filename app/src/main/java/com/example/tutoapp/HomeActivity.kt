package com.example.tutoapp
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutoapp.data.Post

class HomeActivity : AppCompatActivity() {

    lateinit var listePost: ListView
    var postsArray = ArrayList<Post>()
    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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
                showLogoutConfirmDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showLogoutConfirmDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation !")
        builder.setMessage("Ête vous sur de vouloir quitter ?")

        builder.setPositiveButton("Oui") { dialogInterface, id ->
            val editor = this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit()
            // editor.putBoolean("is_authentificated", false)
            // Ou alors :
            editor.remove("is_authentificated")
            editor.apply()
            finish()
        }

        builder.setNegativeButton("Non") {dialogInterface, id ->
            dialogInterface.dismiss()
        }

        builder.setNeutralButton("Annuler") {dialogInterface, id ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()

        alertDialog.show()
    }
}