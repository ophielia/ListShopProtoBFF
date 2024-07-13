package com.listshop.bffpoc.client.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.AppInfo
import com.listshop.bff.ucp.TagUCP
import com.listshop.bff.startSDK
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val analytics = object : Analytics {
            override fun sendEvent(eventName: String, eventArgs: Map<String, Any>) {
                println("eventName: ${eventName}, eventArgs: ${eventArgs.keys.joinToString(",") { key -> "[$key, ${eventArgs[key]}]" }}")
            }
        }
        val appInfo = AppInfo("dummyUrl",
            "name",
            "model",
            "os",
            "osVersion",
            "1.1.1",
            "111",
            "randomDeviceId"
        )
        val sdkHandle = startSDK(analytics, this, appInfo)
        val koinApplication = startKoin {
            modules(
                module {
                    single<Context> { this@MainApp }
                    single<TagUCP> { sdkHandle.tagUCP }
                    single<SharedPreferences> {
                        get<Context>().getSharedPreferences(
                            "KAMPSTARTER_SETTINGS",
                            Context.MODE_PRIVATE
                        )
                    }
                    single {
                        { Log.i("Startup", "Hello from Android/Kotlin!") }
                    }
                }
            )
        }

    }
}