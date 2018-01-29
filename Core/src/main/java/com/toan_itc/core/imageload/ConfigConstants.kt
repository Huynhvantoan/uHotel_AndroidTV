package com.toan_itc.core.imageload

import com.facebook.common.util.ByteConstants

object ConfigConstants {
    val MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB
    private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()
    val MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4
}
