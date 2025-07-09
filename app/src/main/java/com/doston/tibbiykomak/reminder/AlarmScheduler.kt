package com.doston.tibbiykomak.reminder


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.doston.tibbiykomak.data.ReminderData
import java.util.*

object AlarmScheduler {

    fun scheduleAlarmsForPill(context: Context, pill: ReminderData) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val days = pill.day.toIntOrNull() ?: return

        for (i in 0 until days) {
            for ((index, time) in pill.times.withIndex()) {
                val parts = time.split(":")
                if (parts.size != 2) continue

                val hour = parts[0].toIntOrNull() ?: continue
                val minute = parts[1].toIntOrNull() ?: continue

                val calendar = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, i)
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                val intent = Intent(context, PillAlarmReceiver::class.java).apply {
                    putExtra("pillName", pill.name)
                    putExtra("desc", pill.desc)
                }

                val requestCode = generateRequestCode(pill.id ?: 0, index, i)
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    fun cancelAlarmsForPill(context: Context, pill: ReminderData) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val days = pill.day.toIntOrNull() ?: return

        for (i in 0 until days) {
            for (index in pill.times.indices) {
                val intent = Intent(context, PillAlarmReceiver::class.java)
                val requestCode = generateRequestCode(pill.id ?: 0, index, i)
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                alarmManager.cancel(pendingIntent)
            }
        }
    }

    private fun generateRequestCode(id: Int, timeIndex: Int, dayOffset: Int): Int {
        return id * 10000 + dayOffset * 100 + timeIndex
    }
}
