package com.easydroid.serviceuicommunication.livedata

import androidx.lifecycle.LiveData

class MessageLiveData : LiveData<String>() {
    fun setMessage(message: String){
        postValue(message)
    }
}