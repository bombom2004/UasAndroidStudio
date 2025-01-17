package com.stmik.op

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Menyesuaikan padding untuk insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menghubungkan elemen dari layout
        val editTextPanjang: EditText = findViewById(R.id.editTextPanjang)
        val editTextLebar: EditText = findViewById(R.id.editTextLebar)
        val buttonHitung: Button = findViewById(R.id.buttonHitung)
        val textViewHasil: TextView = findViewById(R.id.textViewHasil)
        val buttonLogout: Button = findViewById(R.id.buttonLogout)
        val buttonDetail: Button = findViewById(R.id.buttonDetail)
        val buttonProfile: Button = findViewById(R.id.buttonProfile)

        // Logika untuk tombol hitung
        buttonHitung.setOnClickListener {
            val panjangStr = editTextPanjang.text.toString()
            val lebarStr = editTextLebar.text.toString()

            if (panjangStr.isEmpty() || lebarStr.isEmpty()) {
                // Validasi jika input kosong
                Toast.makeText(this, "Masukkan panjang dan lebar!", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    // Mengubah string input ke angka
                    val panjang = panjangStr.toDouble()
                    val lebar = lebarStr.toDouble()

                    // Hitung luas
                    val luas = panjang * lebar

                    // Tampilkan hasil
                    textViewHasil.text = "Hasil: $luas"
                } catch (e: NumberFormatException) {
                    // Validasi jika input tidak valid
                    Toast.makeText(this, "Input tidak valid!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Logika untuk tombol logout dengan konfirmasi
        buttonLogout.setOnClickListener {
            // Tampilkan dialog konfirmasi
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Arahkan kembali ke halaman login (homeActivity)
                    val intent = Intent(this, homeActivity::class.java)
                    startActivity(intent)
                    finish() // Tutup MainActivity
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss() // Tutup dialog jika pilih Tidak
                }
                .create()
                .show()
        }

        // Logika untuk tombol detail yang membuka halaman DetailActivity
        buttonDetail.setOnClickListener {
            // Arahkan ke DetailActivity
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // Logika untuk tombol profile
        buttonProfile.setOnClickListener {
            // Pindah ke halaman profileActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}