package com.doston.tibbiykomak.reminder


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.doston.tibbiykomak.data.ReminderData
import java.util.*

object AlarmScheduler {

    @SuppressLint("ScheduleExactAlarm")
    fun scheduleAlarmsForPill(context: Context, pill: ReminderData) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for ((dateIndex, dateString) in pill.date.withIndex()) {
            val dateParts = dateString.split("-")
            if (dateParts.size != 3) continue

            val year = dateParts[0].toIntOrNull() ?: continue
            val month = dateParts[1].toIntOrNull()?.minus(1) ?: continue // Calendar.MONTH is 0-based
            val day = dateParts[2].toIntOrNull() ?: continue

            for ((timeIndex, time) in pill.times.withIndex()) {
                val timeParts = time.split(":")
                if (timeParts.size != 2) continue

                val hour = timeParts[0].toIntOrNull() ?: continue
                val minute = timeParts[1].toIntOrNull() ?: continue

                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, day)
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                // Skip past alarms
                if (calendar.timeInMillis <= System.currentTimeMillis()) continue

                val intent = Intent(context, PillAlarmReceiver::class.java).apply {
                    putExtra("pillName", pill.name)
                    putExtra("desc", pill.desc)
                }

                val requestCode = generateRequestCode(pill.id ?: 0, dateIndex, timeIndex)
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        }
    }


    fun cancelAlarmsForPill(context: Context, pill: ReminderData) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for ((dateIndex, _) in pill.date.withIndex()) {
            for ((timeIndex, _) in pill.times.withIndex()) {
                val intent = Intent(context, PillAlarmReceiver::class.java)
                val requestCode = generateRequestCode(pill.id ?: 0, dateIndex, timeIndex)
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
