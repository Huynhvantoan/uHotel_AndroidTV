package com.toan_itc.core.base.event

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v4.util.ArrayMap

@Suppress("UNCHECKED_CAST")
class LiveBus {
    private val mEventMap: MutableMap<Class<out Event>, LiveEvent<out Event>>

    init {
        mEventMap = ArrayMap()
    }

    fun <T : Event> observe(lifecycleOwner: LifecycleOwner, eventClass: Class<T>, observer: Observer<T>) {
        var liveEvent: LiveEvent<T>? = mEventMap[eventClass] as LiveEvent<T>?
        if (liveEvent == null) {
            liveEvent = initLiveEvent(eventClass)
        }
        liveEvent.observe(lifecycleOwner, observer)
    }

    fun <T : Event> send(event: T) {
        var liveEvent: LiveEvent<T>? = mEventMap[event.javaClass] as LiveEvent<T>?
        if (liveEvent == null) {
            liveEvent = initLiveEvent(event.javaClass)
        }
        liveEvent.value = event
    }


    private fun <T : Event> initLiveEvent(eventClass: Class<T>): LiveEvent<T> {
        val liveEvent = LiveEvent<T>()
        mEventMap.put(eventClass, liveEvent)
        return liveEvent
    }
}
