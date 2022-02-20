package com.terry.jetpackmvvm.sample.service

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log


class CountDownService : Service() {

    private lateinit var timer: CountDownTimer

    override fun onCreate() {
        Log.d("CountDownService", "onCreate")
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("CountDownService", "onTick" + +millisUntilFinished / 1000)

            }

            override fun onFinish() {
                Log.d("CountDownService", "onFinish")
            }
        }.start()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d("CountDownService", "onDestroy")
        timer.cancel()
    }
}