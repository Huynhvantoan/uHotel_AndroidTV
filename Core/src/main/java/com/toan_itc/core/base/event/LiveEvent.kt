package com.toan_itc.core.base.event

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

import java.util.concurrent.atomic.AtomicBoolean


// source: https://github.com/googlesamples/android-architecture-components/issues/63
class LiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)


    @MainThread
    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        // observe the internal MutableLiveData
        super.observe(lifecycleOwner, Observer { value ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(value)
            }
        })
    }


    @MainThread
    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }


    @MainThread
    fun call() {
        value = null
    }
}
