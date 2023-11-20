package com.example.foodease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    lateinit var LoginInButton: Button
    lateinit var SigninInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        LoginInButton = findViewById(R.id.LoginInButton)
        SigninInButton = findViewById(R.id.SigninInButton)

        LoginInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            this.startActivity(intent)
        }

        SigninInButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            this.startActivity(intent)
        }
    }
}