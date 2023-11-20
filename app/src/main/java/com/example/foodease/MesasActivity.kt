package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MesasActivity : AppCompatActivity() {

    lateinit var ImagenButtonComida: ImageButton
    lateinit var imageButtonMenu: ImageButton
    lateinit var imageButtonBebidas: ImageButton
    lateinit var imageButtonSalones: ImageButton
    lateinit var btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        setup()
    }
    private fun setup() {

        ImagenButtonComida = findViewById(R.id.ImagenButtonCom)
        imageButtonMenu = findViewById(R.id.imageButtonMen)
        imageButtonBebidas = findViewById(R.id.imageButtonBeb)
        imageButtonSalones = findViewById(R.id.imageButtonSal)
        btn = findViewById(R.id.carritoButton)
        imageButtonMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
            finish()
        }
        ImagenButtonComida.setOnClickListener {
            val intent = Intent(this, ComidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonBebidas.setOnClickListener {
            val intent = Intent(this, BebidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonSalones.setOnClickListener {
            val intent = Intent(this, SalonActivity::class.java)
            this.startActivity(intent)
        }
        btn.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            this.startActivity(intent)
        }
    }
}