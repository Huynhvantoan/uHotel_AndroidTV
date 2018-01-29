package com.toan_itc.core.rxbus

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


/**
 * Created by Toan.IT on 11/2/17.
 * Email:Huynhvantoan.itc@gmail.com
 */
class RxBus {

    private val _bus = PublishRelay.create<Any>().toSerialized()

    fun send(o: Any) {
        _bus.accept(o)
    }

    fun asFlowable(): Flowable<Any> {
        return _bus.toFlowable(BackpressureStrategy.LATEST)
    }

    fun hasObservers(): Boolean {
        return _bus.hasObservers()
    }
}
