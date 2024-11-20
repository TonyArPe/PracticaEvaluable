package com.example.practicaevaluable

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaevaluable.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Guardar el nombre del jugador
        binding.buttonSave.setOnClickListener {
            val nombreJugador = binding.editTextDatos.text.toString()
            if (nombreJugador.isNotEmpty()) {
                // Guardar el nombre en SharedPreferences
                val sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("playerName", nombreJugador)
                editor.apply()

                // Mostrar mensaje de éxito
                Toast.makeText(this, "Nombre guardado: $nombreJugador", Toast.LENGTH_SHORT).show()

                // Regresar a DadosActivity
                finish()
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre.", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para regresar al juego
        binding.buttonBack.setOnClickListener {
            finish()
        }



        // Configuración del Spinner de tiempo
        binding.spinnerTiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val tiempoSeleccionado = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@ConfigActivity, "Tiempo seleccionado: $tiempoSeleccionado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Configuración del ToggleButton
        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            val estado = if (isChecked) "Activado" else "Desactivado"
            Toast.makeText(this, "ToggleButton: $estado", Toast.LENGTH_SHORT).show()
        }

        // Configuración del RadioGroup para opciones
        binding.radioGroupOpciones.setOnCheckedChangeListener { group, checkedId ->
            val opcionSeleccionada = when (checkedId) {
                binding.radioOpcion1.id -> "Opción 1"
                binding.radioOpcion2.id -> "Opción 2"
                else -> ""
            }
            Toast.makeText(this, "RadioButton seleccionado: $opcionSeleccionada", Toast.LENGTH_SHORT).show()
        }

        // Configuración de los CheckBoxes
        binding.checkOpcion1.setOnCheckedChangeListener { _, isChecked ->
            val mensaje = if (isChecked) "CheckBox 1 activado" else "CheckBox 1 desactivado"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        binding.checkOpcion2.setOnCheckedChangeListener { _, isChecked ->
            val mensaje = if (isChecked) "CheckBox 2 activado" else "CheckBox 2 desactivado"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        // Configuración del EditText
        binding.editTextDatos.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val textoIngresado = binding.editTextDatos.text.toString()
                Toast.makeText(this, "Texto agregado: $textoIngresado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
