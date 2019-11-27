package com.example.empresas_android.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class CoroutineViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    protected val jobs = ArrayList<Job>()

    infix fun ArrayList<Job>.add(job: Job) { this.add(job) }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach{ if(!it.isCancelled) it.cancel() }
    }

}