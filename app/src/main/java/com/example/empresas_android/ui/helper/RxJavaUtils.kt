package com.example.empresas_android.ui.helper

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applyIoScheduler(): Observable<T> = applyScheduler(Schedulers.io())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
