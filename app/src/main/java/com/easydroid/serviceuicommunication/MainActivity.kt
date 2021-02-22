package com.easydroid.serviceuicommunication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.easydroid.serviceuicommunication.livedata.LiveDataManager
import com.easydroid.serviceuicommunication.service.MessageService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var messageObserver:Observer<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMessageObserver()

        sendMessage.setOnClickListener {
            setMessageObserver()
            sendMessageToService(messageToSend.text.toString())
        }
    }

    private fun sendMessageToService(message: String) {
        val intentToService = Intent(this, MessageService::class.java)
        intentToService.putExtra(SERVICE_INTENT_EXTRA, message)
        this.startService(intentToService)
    }

    private fun setMessageObserver() {
        messageObserver = Observer { message ->
            serviceResponse.text = message
            removeObserver()
        }

        messageObserver?.let {
            LiveDataManager.messageLiveData().observe(this,
                it)
        }
    }

    private fun removeObserver() {
        messageObserver?.let {
            LiveDataManager.messageLiveData().removeObserver(it)
        }
    }

    companion object {
        const val SERVICE_INTENT_EXTRA = "service_intent_extra_content"
    }
}