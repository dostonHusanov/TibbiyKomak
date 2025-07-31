package com.doston.tibbiykomak.reminder

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.home.FullScreenAlarmActivity

class PillAlarmReceiver : BroadcastReceiver() {
    @SuppressLint("ObsoleteSdkInt", "NotificationPermission")
    override fun onReceive(context: Context, intent: Intent) {
        val pillName = intent.getStringExtra("pillName") ?: "Dori"
        val desc = intent.getStringExtra("desc") ?: "Dori haqida"

        val channelId = "pill_channel"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val fullScreenIntent = Intent(context, FullScreenAlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pillName", pillName)
            putExtra("desc", desc)
        }

        val fullScreenPendingIntent = PendingIntent.getActivity(
            context,
            0,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Pill Reminder",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for pill reminders"
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.pill)
            .setContentTitle(context.getString(R.string.dori_vaqti, pillName))
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setAutoCancel(true)

        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
