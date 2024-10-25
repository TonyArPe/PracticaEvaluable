package com.example.practicaevaluable

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            }
            startActivity(alarmIntent)
        }

        // Botón de abrir reproductor de musica
        val customButton = findViewById<Button>(R.id.customButton)
        customButton.setOnClickListener {
            val customIntent = Intent(Intent.ACTION_VIEW, Uri.parse("spotify:track:6rqhFgbbKwnb9MLmUQDhG6"))
            startActivity(customIntent)
        }
    }
}