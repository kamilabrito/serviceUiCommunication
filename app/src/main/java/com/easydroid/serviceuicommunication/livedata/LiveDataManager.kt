package com.easydroid.serviceuicommunication.livedata

class LiveDataManager {

    companion object {
        private val messageLiveData = MessageLiveData()

        fun updateBackendMessage(message: String) {
            messageLiveData.setMessage(message)
        }

        fun messageLiveData() = messageLiveData

    }
}