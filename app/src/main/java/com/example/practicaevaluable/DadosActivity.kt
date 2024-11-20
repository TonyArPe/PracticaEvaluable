package com.example.practicaevaluable

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaevaluable.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosBinding
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar eventos
        binding.txtResultado.visibility = View.INVISIBLE
        binding.imageButton.setOnClickListener {
            animateDiceRoll()
        }

        // Botón para regresar al MainActivity
        binding.buttonBackMain.setOnClickListener {
            finish()
        }

        // Botón para abrir ConfigActivity
        binding.buttonConfig.setOnClickListener {
            val configIntent = Intent(this, ConfigActivity::class.java)
            startActivity(configIntent)
        }
    }

    // Animación del vaso
    private fun animateDiceRoll() {
        val animator = ObjectAnimator.ofFloat(binding.imageButton, "translationY", -50f, 50f)
        animator.duration = 500
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()

        animator.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                super.onAnimationEnd(animation)
                scheduleRun() // Llamamos al método para continuar con el lanzamiento de los dados
            }
        })
    }

    // Lógica del juego
    private fun scheduleRun() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000L

        for (i in 1..5) {
            schedulerExecutor.schedule(
                { throwDadoInTime() },
                msc * i, TimeUnit.MILLISECONDS
            )
        }

        schedulerExecutor.schedule({
            viewResult()
        }, msc * 7, TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()
    }

    private fun throwDadoInTime() {
        val numDados = Array(3) { Random.nextInt(1, 7) }
        val imageViews: Array<ImageView> = arrayOf(
            binding.imagviewDado1,
            binding.imagviewDado2,
            binding.imagviewDado3
        )

        sum = numDados.sum()

        for (i in imageViews.indices)
            selectView(imageViews[i], numDados[i])
    }

    private fun selectView(imgV: ImageView, v: Int) {
        when (v) {
            1 -> imgV.setImageResource(R.drawable.dado1)
            2 -> imgV.setImageResource(R.drawable.dado2)
            3 -> imgV.setImageResource(R.drawable.dado3)
            4 -> imgV.setImageResource(R.drawable.dado4)
            5 -> imgV.setImageResource(R.drawable.dado5)
            6 -> imgV.setImageResource(R.drawable.dado6)
        }
    }

    private fun viewResult() {
        binding.txtResultado.text = sum.toString()

        // Enviar el resultado a ChistesActivity
        val intent = Intent(this, ChistesActivity::class.java)
        intent.putExtra("resultadoDados", sum)
        startActivity(intent)
    }
}
