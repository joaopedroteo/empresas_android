package com.example.empresas_android.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.empresas_android.ui.CallBackBasicViewModel
import javax.inject.Inject

open class BaseViewModel(private val callback: CallBackBasicViewModel) : ViewModel() {


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