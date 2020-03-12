package com.example.timetracker

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        lateinit var key: String
        fun applicationContext() : MyApplication {
            return instance as MyApplication
        }
        fun setkey(loginKey: String) {
            key=loginKey
        }
    }
}