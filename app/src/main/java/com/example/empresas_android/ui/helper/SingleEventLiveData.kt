package com.example.empresas_android.ui.helper

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)
    private val observersFieldsLogin = mutableSetOf<Observer<in T>>()

    private val internalObserver = Observer<T> { t ->
        if (pending.compareAndSet(true, false)) {
            observersFieldsLogin.forEach { observer ->
                observer.onChanged(t)
            }
        }
    }

    fun observeFieldsLogin(owner: LifecycleOwner, observer: Observer<in T>) {
        observersFieldsLogin.add(observer)
        if (!hasObservers()) {
            super.observe(owner, internalObserver)
        }
    }

    override fun removeObservers(owner: LifecycleOwner) {
        observersFieldsLogin.clear()
        super.removeObservers(owner)
    }

    override fun removeObserver(observer: Observer<in T>) {
        observersFieldsLogin.remove(observer)
        super.removeObserver(observer)
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

}