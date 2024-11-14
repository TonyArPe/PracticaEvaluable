package com.example.practicaevaluable

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaevaluable.databinding.ActivityChistesBinding
import java.util.Locale

class ChistesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChistesBinding
    private lateinit var tts: TextToSpeech
    private val chistes = listOf(
        "¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.",
        "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!",
        "¿Cuál es el colmo de Aladdín? Tener mal genio.",
        "¿Por qué el libro de matemáticas se deprimió? Porque tenía demasiados problemas.",
        "¿Qué le dijo una impresora a otra? Ese papel es tuyo o es una impresión mía?",
        "¿Cómo organizan una fiesta los átomos? Hacen un ion de lío.",
        "¿Por qué los esqueletos no pelean entre ellos? Porque no tienen agallas.",
        "¿Qué hace una vaca con los ojos cerrados? Leche concentrada.",
        "¿Por qué el fantasma no fue a la fiesta? Porque no tenía cuerpo.",
        "¿Qué le dice una iguana a su hermana gemela? Somos iguanitas."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar TextToSpeech
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.getDefault()
            }
        }

        // Configurar el botón para generar chistes
        binding.buttonChisteAleatorio.setOnClickListener {
            val chisteAleatorio = chistes.random()
            binding.textViewChiste.text = chisteAleatorio
            tts.speak(chisteAleatorio, TextToSpeech.QUEUE_FLUSH, null, "")
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}

