package com.example.practicaevaluable

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfiguracionLlamadas : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var phoneNumberEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)

        // Cargar el número guardado si existe
        val savedPhoneNumber = sharedPreferences.getString("phone_number", "")
        phoneNumberEditText.setText(savedPhoneNumber)

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("phone_number", phoneNumber)
            editor.apply()
            Toast.makeText(this, "Número guardado", Toast.LENGTH_SHORT).show()
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}
