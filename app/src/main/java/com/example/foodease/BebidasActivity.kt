package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class BebidasActivity : AppCompatActivity() {

    lateinit var ImagenButtonComida: ImageButton
    lateinit var imageButtonMenu: ImageButton
    lateinit var imageButtonMesas: ImageButton
    lateinit var imageButtonSalones: ImageButton
    lateinit var buttonJugos: Button
    lateinit var ButtonEmbotellado: Button
    lateinit var buttonFrapes: Button
    lateinit var buttonCafe: Button
    lateinit var buttonJugoEmbotellado: Button
    lateinit var btn: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bebidas)

        setup()
    }
    private fun setup() {

        ImagenButtonComida = findViewById(R.id.ImagenButtonCom)
        imageButtonMenu = findViewById(R.id.imageButtonMen)
        imageButtonMesas = findViewById(R.id.imageButtonMes)
        imageButtonSalones = findViewById(R.id.imageButtonSal)
        buttonJugos = findViewById(R.id.buttonJugos)
        ButtonEmbotellado = findViewById(R.id.ButtonEmbotellado)
        buttonFrapes = findViewById(R.id.buttonFrapes)
        buttonCafe = findViewById(R.id.buttonCafe)
        buttonJugoEmbotellado = findViewById(R.id.buttonJugoEmbotellado)
        btn = findViewById(R.id.carritoButton)
        imageButtonMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        }
        ImagenButtonComida.setOnClickListener {
            val intent = Intent(this, ComidasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonMesas.setOnClickListener {
            val intent = Intent(this, MesasActivity::class.java)
            this.startActivity(intent)
        }
        imageButtonSalones.setOnClickListener {
            val intent = Intent(this, SalonActivity::class.java)
            this.startActivity(intent)
        }
        buttonJugos.setOnClickListener {
            val intent = Intent(this, MostrarActivityBebidas::class.java)
            this.startActivity(intent)
        }
        ButtonEmbotellado.setOnClickListener {
            val intent = Intent(this, MostrarActivityBebidasEmbotellados::class.java)
            this.startActivity(intent)
        }
        buttonFrapes.setOnClickListener {
            val intent = Intent(this, MostrarActivityBebidasFrapes::class.java)
            this.startActivity(intent)
        }
        buttonCafe.setOnClickListener {
            val intent = Intent(this, MostrarActivityBebidasCafe::class.java)
            this.startActivity(intent)
        }
        buttonJugoEmbotellado.setOnClickListener {
            val intent = Intent(this, MostrarActivityBebidasJugoEmbotellado::class.java)
            this.startActivity(intent)
        }
        btn.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            this.startActivity(intent)
        }
    }
}