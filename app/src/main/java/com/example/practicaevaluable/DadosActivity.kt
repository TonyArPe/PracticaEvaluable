package com.example.practicaevaluable

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaevaluable.databinding.ActivityDadosBinding
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosBinding
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el nombre del jugador desde SharedPreferences
        val sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)
        val playerName = sharedPreferences.getString("playerName", "Jugador no asignado")

        // Mostrar el nombre del jugador en el TextView
        binding.txtJugadorNombre.text = "Jugador: $playerName"

        // Configurar los demás botones y funcionalidades de la actividad
        binding.buttonConfig.setOnClickListener {
            // Ir a la ConfigActivity para cambiar el nombre del jugador
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        binding.imageButton.setOnClickListener {
            lanzarDadosAnimacion()
        }

        binding.buttonBackMain.setOnClickListener { finish() }

        binding.buttonConfig.setOnClickListener {
            // Redirigir a ConfigActivity
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }
    }

    private fun lanzarDadosAnimacion() {
        val animacion = ObjectAnimator.ofFloat(binding.imageButton, "rotation", 0f, 360f)
        animacion.duration = 500
        animacion.start()

        animacion.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                lanzarDados()
                irAChistesActivity() // Redirigir a ChistesActivity con el resultado
            }
        })
    }

    private fun lanzarDados() {
        val resultados = List(3) { Random.nextInt(1, 7) }
        val dados = listOf(binding.imagviewDado1, binding.imagviewDado2, binding.imagviewDado3)
        resultados.forEachIndexed { index, resultado ->
            val resId = resources.getIdentifier("dado$resultado", "drawable", packageName)
            dados[index].setImageResource(resId)
        }
        sum = resultados.sum()
        binding.txtResultado.text = "Resultado: $sum"
    }

    private fun irAChistesActivity() {
        val intent = Intent(this, ChistesActivity::class.java)
        intent.putExtra("resultado_dados", sum) // Pasar el resultado a ChistesActivity
        startActivity(intent)
    }
}
