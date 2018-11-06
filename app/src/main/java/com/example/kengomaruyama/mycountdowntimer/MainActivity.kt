package com.example.kengomaruyama.mycountdowntimer

import android.icu.util.DateInterval
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.CompletableFuture

class MainActivity : AppCompatActivity() {

    inner class MyCountDownTimer(millisInFuture: Long,
                                 countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

        var isRunning = false

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            timerText.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            timerText.text = "0:00"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText.text = "3:00"
        val timer = MyCountDownTimer(3 * 60 * 1000, 100)
        playStop.setOnClickListener{
            when (timer.isRunning){
                true -> timer.apply {
                    isRunning = false
                    cancel()
                    playStop.setImageResource(
                            R.drawable.ic_play_arrow_black_24dp)
                }
                false -> timer.apply {
                    isRunning = true
                    start()
                    playStop.setImageResource(
                            R.drawable.ic_stop_black_24dp)
                }
            }
        }
    }
}
