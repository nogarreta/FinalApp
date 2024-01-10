package fr.isep.noelle.covoiturage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import fr.isep.noelle.covoiturage.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewWelcome: ImageView = findViewById(R.id.imageViewWelcome)

        val welcomeButton: Button = findViewById(R.id.welcomeButton)

        welcomeButton.setOnClickListener {
            imageViewWelcome.visibility = View.VISIBLE

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}