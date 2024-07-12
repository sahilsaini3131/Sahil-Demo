package com.sahil.demo.controller

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class Controller : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        @JvmStatic
        var context: WeakReference<Context>? = null
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        context = WeakReference(p0)
    }

    override fun onActivityStarted(p0: Activity)=Unit

    override fun onActivityResumed(p0: Activity) {
        context = WeakReference(p0)
    }

    override fun onActivityPaused(p0: Activity)=Unit

    override fun onActivityStopped(p0: Activity)=Unit
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle)=Unit

    override fun onActivityDestroyed(p0: Activity)=Unit
}

    //https://dummy.restapiexample.com/api/v1/employees