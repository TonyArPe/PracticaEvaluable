package com.example.practicaevaluable

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.practicaevaluable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val REQUEST_CALL_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)

        // Botón de Configuración de Llamadas
        binding.configButton.setOnClickListener {
            val configIntent = Intent(this, ConfiguracionLlamadas::class.java)
            startActivity(configIntent)
        }

        // Botón de abrir URL
        binding.urlButton.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/"))
            startActivity(urlIntent)
        }

        // Botón de crear alarma
        binding.alarmButton.setOnClickListener {
            val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma Personalizada")
                putExtra(AlarmClock.EXTRA_HOUR, 0)
                putExtra(AlarmClock.EXTRA_MINUTES, 2)
                putExtra(AlarmClock.EXTRA_VIBRATE, true)
            }
            startActivity(alarmIntent)
        }

        // Botón de abrir reproductor de música
        binding.musicPlayerButton.setOnClickListener {
            val intent = Intent(this, MusicPlayerActivity::class.java)
            startActivity(intent)
        }

        // Botón de llamada telefónica
        binding.callButton.setOnClickListener {
            makePhoneCall()
        }

        // Acceso a ChistesActivity
        binding.buttonVerChistes.setOnClickListener {
            val intent = Intent(this, ChistesActivity::class.java)
            startActivity(intent)
        }

        // Acceso a DadosActivity
        binding.buttonJugarDados.setOnClickListener {
            val intent = Intent(this, DadosActivity::class.java)
            startActivity(intent)
        }

        // Mostrar el ProgressBar durante 2 segundos
        binding.progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.visibility = View.GONE
        }, 2000)
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
