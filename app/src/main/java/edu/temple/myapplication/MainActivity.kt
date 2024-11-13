package edu.temple.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countdownInput = findViewById<EditText>(R.id.editTextCountdown) // Fixed ID to match layout
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val countdownValue = countdownInput.text.toString().toIntOrNull()
            if (countdownValue != null) {
                // Create intent to start the service with the countdown value
                val serviceIntent = Intent(this, TimerService::class.java).apply {
                    putExtra("COUNTDOWN_VALUE", countdownValue)
                }
                startService(serviceIntent)
            }
        }
    }
}