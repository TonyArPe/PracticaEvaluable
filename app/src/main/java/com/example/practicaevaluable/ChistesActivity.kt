package com.example.practicaevaluable

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
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
        "¿Qué le dice una iguana a su hermana gemela? Somos iguanitas.",
        "¿Qué hace un pez en una biblioteca? Nada.",
        "¿Por qué las vacas no se tiran pedos en la iglesia? Porque sería un acto de mala educación.",
        "¿Qué es un café muy fuerte? Uno que te da una buena tunda.",
        "¿Qué hace un árbol en el ordenador? Se enraiza.",
        "¿Por qué no podemos confiar en los árboles? Porque siempre te dejan plantado.",
        "¿Qué le dice un jardinero a otro? Disfrutemos mientras podamos.",
        "¿Por qué las galletas no hablan? Porque se desmigarían.",
        "¿Cómo se despide una tienda de campaña? ¡Hasta la próxima acampada!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el resultado de los dados
        val resultadoDados = intent.getIntExtra("resultado_dados", 0)
        val chiste = obtenerChistePorResultado(resultadoDados)

        // Inicializar TextToSpeech
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("es", "ES")
                reproducirChiste(chiste)
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun obtenerChistePorResultado(resultado: Int): String {
        return chistes[resultado % chistes.size] // Selecciona un chiste basado en el resultado
    }

    private fun reproducirChiste(chiste: String) {
        binding.textViewChiste.text = chiste
        tts.speak(chiste, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
