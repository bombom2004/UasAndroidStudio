package com.stmik.op

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Mengatur padding untuk elemen root agar sesuai dengan insets sistem
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referensi ke elemen layout
        val btnLogin = findViewById<Button>(R.id.buttonLogin)
        val usernameField = findViewById<EditText>(R.id.editTextUsername)
        val passwordField = findViewById<EditText>(R.id.editTextPassword)

        // Tombol login
        btnLogin.setOnClickListener {
            val usernameInput = usernameField.text.toString()
            val passwordInput = passwordField.text.toString()

            // Validasi login sederhana
            val correctUsername = "bom"
            val correctPassword = "123"

            if (usernameInput == correctUsername && passwordInput == correctPassword) {
                // Login berhasil - pindah ke MainActivity
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Tutup homeActivity agar tidak bisa kembali dengan tombol Back
            } else {
                // Login gagal
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
