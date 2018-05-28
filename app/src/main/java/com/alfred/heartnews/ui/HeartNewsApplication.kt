package com.alfred.heartnews.ui

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Created by alfred on 2018/3/21.
 */
class HeartNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
