package com.doston.tibbiykomak.home

import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.databinding.ActivityFullScreenAlarmBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FullScreenAlarmActivity : Activity() {
    private lateinit var binding: ActivityFullScreenAlarmBinding
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    private val handler = Handler(Looper.getMainLooper())
    private var autoCloseRunnable: Runnable? = null
    private var isAlarmActive = true

    companion object {
        private const val TAG = "FullScreenAlarmActivity"
        private const val AUTO_CLOSE_DELAY = 60000L // 1 minute
        private val VIBRATION_PATTERN = longArrayOf(0, 500, 200, 500, 200, 500)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.mainColor)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.mainColor)
        Log.d(TAG, "FullScreenAlarmActivity started")

        setupWindowFlags()


        binding = ActivityFullScreenAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            findViewById<View>(R.id.main)?.let { mainView ->
                ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }

        val pillName = intent.getStringExtra("pillName") ?: "Dori"
        val pillDesc = intent.getStringExtra("desc") ?: "Eslatma"

        setupUI(pillName, pillDesc)

        setupClickListeners()

        startAlarmEffects()

        setupAutoClose()

        updateCurrentTime()
    }

    private fun setupWindowFlags() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }

        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }

    private fun setupUI(pillName: String, pillDesc: String) {
        binding.apply {
            pillNameText.text = pillName
            pillDescText.text = pillDesc

            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            timeText.text = currentTime

            val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            dateText.text = currentDate

        }
    }

    private fun setupClickListeners() {
        binding.apply {

            dismissButton.setOnClickListener {
                dismissAlarm()
            }

            snoozeButton.setOnClickListener {
                snoozeAlarm()
            }

            takePillButton.setOnClickListener {
                takePill()
            }

            root.setOnClickListener {
                dismissAlarm()
            }
        }
    }

    private fun startAlarmEffects() {
        startAlarmSound()
        startVibration()
    }

    private fun startAlarmSound() {
        try {
            val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

            mediaPlayer = MediaPlayer().apply {
                setDataSource(this@FullScreenAlarmActivity, alarmUri)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                } else {
                    @Suppress("DEPRECATION")
                    setAudioStreamType(AudioManager.STREAM_ALARM)
                }

                isLooping = true
                prepare()
                start()
            }

            Log.d(TAG, "Alarm sound started")
        } catch (e: Exception) {
            Log.e(TAG, "Error starting alarm sound", e)
        }
    }

    private fun startVibration() {
        try {
            vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager =
                    getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            vibrator?.let { vib ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(
                        VibrationEffect.createWaveform(VIBRATION_PATTERN, 0),
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build()
                    )
                } else {
                    @Suppress("DEPRECATION")
                    vib.vibrate(VIBRATION_PATTERN, 0)
                }
            }

            Log.d(TAG, "Vibration started")
        } catch (e: Exception) {
            Log.e(TAG, "Error starting vibration", e)
        }
    }

    private fun stopAlarmEffects() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            mediaPlayer = null
        }

        vibrator?.cancel()
        vibrator = null

        Log.d(TAG, "Alarm effects stopped")
    }

    private fun setupAutoClose() {
        autoCloseRunnable = Runnable {
            if (isAlarmActive) {
                Toast.makeText(this, "Signal vaqti tugadi", Toast.LENGTH_SHORT).show()
                dismissAlarm()
            }
        }
        handler.postDelayed(autoCloseRunnable!!, AUTO_CLOSE_DELAY)
    }

    private fun updateCurrentTime() {
        val timeRunnable = object : Runnable {
            override fun run() {
                if (isAlarmActive) {
                    val currentTime =
                        SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                    binding.timeText.text = currentTime
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(timeRunnable)
    }

    private fun dismissAlarm() {
        isAlarmActive = false
        stopAlarmEffects()
        handler.removeCallbacks(autoCloseRunnable ?: return)

        Toast.makeText(this, "Signal o'chirildi", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Alarm dismissed")

        finish()
    }

    private fun snoozeAlarm() {
        isAlarmActive = false
        stopAlarmEffects()
        handler.removeCallbacks(autoCloseRunnable ?: return)

        Toast.makeText(this, "5 daqiqaga kechiktirildi", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Alarm snoozed")


        finish()
    }

    private fun takePill() {
        isAlarmActive = false
        stopAlarmEffects()
        handler.removeCallbacks(autoCloseRunnable ?: return)

        Toast.makeText(this, "Dori Ichildi! Sog'ayib ketin!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Pill taken")


        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        isAlarmActive = false
        stopAlarmEffects()
        handler.removeCallbacks(autoCloseRunnable ?: return)
        Log.d(TAG, "FullScreenAlarmActivity destroyed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "FullScreenAlarmActivity paused")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "FullScreenAlarmActivity resumed")
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Chiqish uchun ", Toast.LENGTH_SHORT).show()
    }
}