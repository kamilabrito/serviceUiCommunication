package com.easydroid.serviceuicommunication.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.easydroid.serviceuicommunication.MainActivity.Companion.SERVICE_INTENT_EXTRA
import com.easydroid.serviceuicommunication.livedata.LiveDataManager

class MessageService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val ret = super.onStartCommand(intent, flags, startId)

        intent?.getStringExtra(SERVICE_INTENT_EXTRA)?.let { data ->
            val returnMessage = "Service received message: $data"
            LiveDataManager.updateBackendMessage(returnMessage)
        }
        return ret
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}