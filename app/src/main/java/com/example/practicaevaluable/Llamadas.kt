package com.example.practicaevaluable

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Llamadas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamadas)

        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        messageTextView.text = "Realizando llamada..."

        val callButton = findViewById<Button>(R.id.callAgainButton)
        callButton.setOnClickListener {
            // Aquí puedes volver a la actividad principal o realizar otra acción
            finish()
        }
    }
}
