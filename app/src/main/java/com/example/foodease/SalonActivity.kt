package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SalonActivity : AppCompatActivity() {

    lateinit var ImagenButtonComida: ImageButton
    lateinit var imageButtonMenu: ImageButton
    lateinit var imageButtonMesas: ImageButton
    lateinit var imageButtonBebidas: ImageButton
    lateinit var btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon)

        setup()
    }
    private fun setup() {

        imageButtonBebidas = findViewById(R.id.imageButtonBeb)
        imageButtonMenu = findViewById(R.id.imageButtonMen)
        imageButtonMesas = findViewById(R.id.imageButtonMes)
        ImagenButtonComida = findViewById(R.id.ImagenButtonCom)
        btn = findViewById(R.id.carritoButton)
        imageButtonMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonBebidas.setOnClickListener {
            val intent = Intent(this, BebidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonMesas.setOnClickListener {
            val intent = Intent(this, MesasActivity::class.java)
            this.startActivity(intent)
        }
        ImagenButtonComida.setOnClickListener {
            val intent = Intent(this, ComidasActivity::class.java)
            this.startActivity(intent)
        }
        btn.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            this.startActivity(intent)
        }
    }
}