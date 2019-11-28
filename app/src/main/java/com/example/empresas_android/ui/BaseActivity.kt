package com.example.empresas_android.ui

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.empresas_android.R

open class BaseActivity : AppCompatActivity(), CallBackBasicViewModel {

    override fun setTypeFace(textView: TextView, fontPath: String) {
        val typeFace = Typeface.createFromAsset(assets, fontPath)
        textView.typeface = typeFace
    }


    override fun openActivity(openActivity: Class<*>) {
        val intent = Intent(this, openActivity)
        startActivity(intent)
    }

    override fun openActivityAndFinish(openActivity: Class<*>) {
        val intent = Intent(this, openActivity)
        startActivity(intent)
        finish()
    }

    override fun openActivity(openActivity: Class<*>, bundle: Bundle) {
        val intent = Intent(this, openActivity)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    override fun openActivityAfterTimeAndFinish(openActivity: Class<*>, time: Long) {
        Handler().postDelayed({
            val intent = Intent(this, openActivity)
            startActivity(intent)
            finish()
        }, time)
    }


    override fun startIntent(callIntent: Intent) = startActivity(callIntent)

    override fun hasInternetConnection(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            @Suppress("DEPRECATION")
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    override fun showDialog(title: String, message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(getString(R.string.ok)) { _, _ ->
        }
        alertDialog.show()
    }

    override fun showDialog(titleId: Int, messageId: Int) {
        val title = getString(titleId)
        val message = getString(messageId)
        showDialog(title, message)
    }
}