package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.firestore.FirebaseFirestore

class ComidasActivity : AppCompatActivity() {

    lateinit var imageButtonBebidas: ImageButton
    lateinit var imageButtonMenu: ImageButton
    lateinit var imageButtonMesas: ImageButton
    lateinit var imageButtonSalones: ImageButton
    lateinit var ButtonChilaquiles: Button
    lateinit var ButtonTacos: Button
    lateinit var ButtonEmpanadas: Button
    lateinit var ButtonCaldos: Button
    lateinit var ButtonCoctel: Button
    lateinit var Mostrarcomida: Button
    lateinit var btn: ImageButton
    lateinit var miFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comidas)

        setup()
    }
    private fun setup() {
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        imageButtonBebidas = findViewById(R.id.imageButtonBeb)
        imageButtonMenu = findViewById(R.id.imageButtonMen)
        imageButtonMesas = findViewById(R.id.imageButtonMes)
        imageButtonSalones = findViewById(R.id.imageButtonSal)
        ButtonChilaquiles = findViewById(R.id.ButtonChilaquiles)
        ButtonTacos = findViewById(R.id.buttonTacos)
        ButtonEmpanadas = findViewById(R.id.buttonEmpanadas)
        ButtonCaldos = findViewById(R.id.buttonCaldos)
        ButtonCoctel = findViewById(R.id.buttonCoctel)
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
        imageButtonSalones.setOnClickListener {
            val intent = Intent(this, SalonActivity::class.java)
            this.startActivity(intent)
        }
        ButtonChilaquiles.setOnClickListener {
            val intent = Intent(this, MostrarActivity::class.java)
            this.startActivity(intent)
        }
        ButtonTacos.setOnClickListener {
            val intent = Intent(this, MostrarActivityTacos::class.java)
            this.startActivity(intent)
        }
        ButtonEmpanadas.setOnClickListener {
            val intent = Intent(this, MostrarActivityEmpanadas::class.java)
            this.startActivity(intent)
        }
        ButtonCaldos.setOnClickListener {
            val intent = Intent(this, MostrarActivityCaldos::class.java)
            this.startActivity(intent)
        }
        ButtonCoctel.setOnClickListener {
            val intent = Intent(this, MostrarActivityCoctel::class.java)
            this.startActivity(intent)
        }
        btn.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            this.startActivity(intent)
        }
    }
}