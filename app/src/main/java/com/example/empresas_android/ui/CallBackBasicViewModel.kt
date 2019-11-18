package com.example.empresas_android.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle

interface CallBackBasicViewModel {

    fun showDialogProgress()

    fun showDialogProgress(text: Int)

    fun hideDialogProgress()

    fun openActivity(openActivity: Class<*>)

    fun openActivity(openActivity: Class<*>, bundle: Bundle)

    fun openActivityAfterTime(openActivity: Class<*>, time: Long)

    fun openActivityAndClearStack(openActivity: Class<*>)

    fun finish(resultCode: Int)

    fun finish(resultCode: Int, bundle: Bundle)

    fun hideKeyboard()

    fun hasInternetConnection(): Boolean

    fun getString(resString: Int): String

    fun getIntent(): Intent

    fun startIntent(callIntent: Intent)

    fun setDialog(title: String, subTitle: String, alertDialog: AlertDialog)
}