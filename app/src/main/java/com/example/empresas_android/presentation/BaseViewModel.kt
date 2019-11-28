package com.example.empresas_android.presentation

import android.os.Bundle
import com.example.empresas_android.ui.CallBackBasicViewModel

open class BaseViewModel(private val callback: CallBackBasicViewModel) : CoroutineViewModel() {


    protected fun showDialog(titleId:Int, messageId:Int) {
        return callback.showDialog(titleId, messageId)
    }

    protected fun hasInternetConnection(): Boolean {
        return callback.hasInternetConnection()
    }

    protected fun openActivityAndFinish(open: Class<*>) {
        callback.openActivityAndFinish(open)
    }

    protected fun openActivity(open: Class<*>, bundle: Bundle) {
        callback.openActivity(open, bundle)
    }

}