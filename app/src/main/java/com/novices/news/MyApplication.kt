package com.novices.news

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object{
        lateinit var context:Context
        val KAY = "b513b5c035faf86314d91681d648f1be"
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}
