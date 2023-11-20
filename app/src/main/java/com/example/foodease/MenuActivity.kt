package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MenuActivity : AppCompatActivity() {

    lateinit var imageButtonComida: ImageButton
    lateinit var imageButtonBebida: ImageButton
    lateinit var imageButtonMesa: ImageButton
    lateinit var imageButtonPerfil: ImageButton
    lateinit var imageButtonSalon: ImageButton
    lateinit var btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setup()
    }
    private fun setup() {

        imageButtonComida = findViewById(R.id.imageButtonComida)
        imageButtonBebida = findViewById(R.id.imageButtonBebida)
        imageButtonMesa = findViewById(R.id.imageButtonMesa)
        imageButtonPerfil = findViewById(R.id.imageButtonPerfil)
        imageButtonSalon = findViewById(R.id.imageButtonSal)
        btn = findViewById(R.id.carritoButton)
        imageButtonComida.setOnClickListener {
            val intent = Intent(this, ComidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonBebida.setOnClickListener {
            val intent = Intent(this, BebidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonMesa.setOnClickListener {
            val intent = Intent(this, MesasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonPerfil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        btn.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonSalon.setOnClickListener {
            val intent = Intent(this, SalonActivity::class.java)
            this.startActivity(intent)
        }
    }
}