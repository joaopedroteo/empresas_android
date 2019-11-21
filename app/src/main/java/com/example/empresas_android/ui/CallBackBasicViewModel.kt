package com.example.empresas_android.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView

interface CallBackBasicViewModel {

    fun setTypeFace(textView: TextView, fontPath:String)

    fun showDialog(title: String, message: String = "")

    fun openActivity(openActivity: Class<*>)

    fun openActivityAndFinish(openActivity: Class<*>)

    fun openActivity(openActivity: Class<*>, bundle: Bundle)

    fun openActivityAfterTimeAndFinish(openActivity: Class<*>, time: Long)

    fun hasInternetConnection(): Boolean

    fun startIntent(callIntent: Intent)

    fun finish()
}