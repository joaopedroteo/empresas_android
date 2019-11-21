package com.example.empresas_android.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.empresas_android.ui.CallBackBasicViewModel

open class BaseViewModel(val callback: CallBackBasicViewModel) : ViewModel() {


    protected fun hasInternet(): Boolean? {
        return callback.hasInternetConnection()
    }

    protected fun openActivity(open: Class<*>) {
        callback.openActivity(open)
    }

    protected fun openActivityAndFinish(open: Class<*>) {
        callback.openActivityAndFinish(open)
    }

    protected fun openActivity(open: Class<*>, bundle: Bundle) {
        callback.openActivity(open, bundle)
    }


}