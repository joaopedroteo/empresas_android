package com.example.empresas_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.empresas_android.R

open class BaseActivity:AppCompatActivity(), CallBackBasicViewModel {
    private var showingDialog = false
    private var dialog:AlertDialog? = null

    private fun getDialogInstance() : AlertDialog {
        if (dialog == null){
            dialog = AlertDialog.Builder(this)
                .setCancelable(false)
                .create()
        }
        return dialog as AlertDialog
    }

    override fun showDialogProgress() {
        if (!showingDialog) {
            showingDialog = true
            dialog = getDialogInstance()
            dialog?.show()
        }
    }

    override fun showDialogProgress(text: Int) {

    }

    override fun hideDialogProgress() {

    }

    override fun openActivity(openActivity: Class<*>) {

    }

    override fun openActivity(openActivity: Class<*>, bundle: Bundle) {

    }

    override fun openActivityAfterTime(openActivity: Class<*>, time: Long) {

    }

    override fun openActivityAndClearStack(openActivity: Class<*>) {

    }

    override fun finish(resultCode: Int) {

    }

    override fun finish(resultCode: Int, bundle: Bundle) {

    }

    override fun hideKeyboard() {

    }

    override fun hasInternetConnection(): Boolean {
        return true
    }

    override fun startIntent(callIntent: Intent) {

    }

    override fun setDialog(title: String, subTitle: String, alertDialog: android.app.AlertDialog) {

    }

    protected fun callAlert(title: String, message: String = "") {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(getString(R.string.ok)) { _, _ ->
            finish()
        }
        alertDialog.show()
    }


}