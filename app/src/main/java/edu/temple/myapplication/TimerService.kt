package edu.temple.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class TimerService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Get the countdown value from the intent
        val countdownValue = intent?.getIntExtra("COUNTDOWN_VALUE", 0) ?: 0
        
        // Start the countdown using coroutines
        serviceScope.launch {
            startCountdown(countdownValue)
        }
        
        return START_NOT_STICKY
    }

    private suspend fun startCountdown(fromValue: Int) {
        for (i in fromValue downTo 0) {
            Log.d("TimerService", "Countdown: $i")
            delay(1000) // Wait for 1 second
        }
        Log.d("TimerService", "Countdown finished!")
    }

    override fun onBind(intent: Intent?): IBinder? {
        // This is a started service, not a bound service
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel all coroutines when the service is destroyed
        serviceScope.cancel()
    }
}