package com.example.practicaevaluable

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val REQUEST_CALL_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)

        // Botón de Configuracion de Llamadas
        val configButton = findViewById<Button>(R.id.configButton)
        configButton.setOnClickListener {
            val configIntent = Intent(this, Configuracion::class.java)
            startActivity(configIntent)
        }


        // Botón de abrir URL
        val urlButton = findViewById<Button>(R.id.urlButton)
        urlButton.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/"))
            startActivity(urlIntent)
        }

        // Botón de crear alarma
        val alarmButton = findViewById<Button>(R.id.alarmButton)
        alarmButton.setOnClickListener {
            val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma Personalizada")
                putExtra(AlarmClock.EXTRA_HOUR, 0)
                putExtra(AlarmClock.EXTRA_MINUTES, 2)
                putExtra(AlarmClock.EXTRA_VIBRATE, true)
            }
            startActivity(alarmIntent)
        }

        // Botón de abrir reproductor de música
        val musicPlayerButton = findViewById<Button>(R.id.musicPlayerButton)
        musicPlayerButton.setOnClickListener {
            val intent = Intent(this, MusicPlayerActivity::class.java)
            startActivity(intent)
        }

        // Botón de llamada telefónica
        val callButton = findViewById<Button>(R.id.callButton)
        callButton.setOnClickListener {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        val savedPhoneNumber = sharedPreferences.getString("phone_number", null)
        if (savedPhoneNumber != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PERMISSION)
            } else {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$savedPhoneNumber")))
            }
        } else {
            Toast.makeText(this, "No se ha configurado un número de teléfono", Toast.LENGTH_SHORT).show()
        }
    }
}
