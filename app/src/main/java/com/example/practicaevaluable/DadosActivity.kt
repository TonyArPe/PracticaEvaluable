package com.example.practicaevaluable

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaevaluable.databinding.ActivityDadosBinding

class DadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*

        binding.buttonLanzarDados.setOnClickListener {
            val resultadoDado = (1..6).random()
            binding.textViewResultado.text = "Resultado: $resultadoDado"
            // Aquí podrías mostrar una imagen de una carta o el dado
        }
    }
    */
    }
}
