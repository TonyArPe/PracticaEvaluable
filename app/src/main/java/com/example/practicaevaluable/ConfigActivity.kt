package com.example.practicaevaluable

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaevaluable.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el Spinner
        binding.spinnerTiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val tiempoSeleccionado = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@ConfigActivity, "Tiempo seleccionado: $tiempoSeleccionado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Configurar el ToggleButton
        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            val estado = if (isChecked) "Activado" else "Desactivado"
            Toast.makeText(this, "ToggleButton: $estado", Toast.LENGTH_SHORT).show()
        }

        // Configura los RadioButtons y CheckBoxes si es necesario
    }
}

