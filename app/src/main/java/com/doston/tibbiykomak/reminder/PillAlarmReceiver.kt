package com.doston.tibbiykomak.reminder


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.doston.tibbiykomak.R

class PillAlarmReceiver : BroadcastReceiver() {
    @SuppressLint("ObsoleteSdkInt", "NotificationPermission")
    override fun onReceive(context: Context, intent: Intent) {
        val pillName = intent.getStringExtra("pillName") ?: "Dori"
        val desc = intent.getStringExtra("desc") ?: "Dori haqida"

        val channelId = "pill_channel"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Pill Reminder", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.pill) // make sure you have this icon
            .setContentTitle("Dori vaqti: $pillName")
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
