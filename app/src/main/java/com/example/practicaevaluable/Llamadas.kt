package com.example.practicaevaluable

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Llamadas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamadas)

        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        messageTextView.text = "Realizando llamada..."

        val callButton = findViewById<Button>(R.id.callAgainButton)
        callButton.setOnClickListener {
            checkAndRequestCallPermission()
        }
    }

    private fun checkAndRequestCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:611496339")
        startActivity(intent)
        finish()
    }
}
