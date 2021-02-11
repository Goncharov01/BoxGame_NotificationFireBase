package com.example.magicbarrel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
        const val CHANNEL_NAME = "Notification"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonNotify)

        button.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, SecondActivity::class.java)
            intent.apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val notificationManager = NotificationManagerCompat.from(this)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Напоминание")
                .setContentText("Пора пить кофе")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(NOTIFICATION_ID, builder.build())

        })

        win.setOnClickListener(View.OnClickListener {

            choose_group.visibility = View.VISIBLE
            win.visibility = View.GONE

        })

        lose.setOnClickListener(View.OnClickListener {

            lose.setOnClickListener(View.OnClickListener {
                choose_group.visibility = View.VISIBLE
                lose.visibility = View.GONE
            })

        })

    }

    fun click(v: View) {

        if (Math.random() <= 0.40) {
            choose_group.visibility = View.GONE
            win.visibility = View.VISIBLE
            Toast.makeText(this, "win", Toast.LENGTH_SHORT).show()

        } else {

            choose_group.visibility = View.GONE
            lose.visibility = View.VISIBLE
            Toast.makeText(this, "lose", Toast.LENGTH_SHORT).show()

        }
    }

}


