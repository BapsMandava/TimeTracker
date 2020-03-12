package com.example.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buffer.*
import kotlinx.android.synthetic.main.activity_main.*

class BufferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer)
        if(intent.getIntExtra("Type",-1)==0){
            clockInClockOutBufferScreen(0)
        } else {
            clockInClockOutBufferScreen(1)
        }
        txtCancel.setOnClickListener {
            Toast.makeText(this, "Checking Cancelled", Toast.LENGTH_SHORT).show()
            setResult(-1)
            finish()
        }
    }



    private fun clockInClockOutBufferScreen(type: Int) {
        llBufferTimeLayout.visibility = View.VISIBLE
        if (type == 0) {
            txtClockingType.setText("Clocking In...")
        } else {
            txtClockingType.setText("Clocking Out...")
        }
        var progress = 0
        val pBarMax = 10
        progress_bar.max = pBarMax
        val pBarThread = object : Thread() {
            override fun run() {
                try {
                    while (progress <= pBarMax) {
                        progress_bar.progress = progress
                        Thread.sleep(1000)
                        ++progress
                    }
                    setResult(type)
                    finish()

                } catch (e: InterruptedException) {
                }
            }
        }
        pBarThread.start()
    }
}
