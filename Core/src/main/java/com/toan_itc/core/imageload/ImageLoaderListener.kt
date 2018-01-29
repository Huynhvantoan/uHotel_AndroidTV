package com.toan_itc.core.imageload

import android.content.Context
import android.graphics.Bitmap
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.Observable

/**
 * Created by Toan.IT on 6/22/17.
 * Email: huynhvantoan.itc@gmail.com
 */

interface ImageLoaderListener {

    fun loadImage(url: String, simpleDraweeView: SimpleDraweeView?)

    fun loadController(url: String, simpleDraweeView: SimpleDraweeView?, width: Int, height: Int, controllerListener: ControllerListener<Any>?)

    fun loadController(url: String, simpleDraweeView: SimpleDraweeView?, width: Float, height: Float)

    fun loadController(source: Int, simpleDraweeView: SimpleDraweeView?, width: Float, height: Float)

    fun loadHierarchy(url: String, simpleDraweeView: SimpleDraweeView)

    fun loadBitmap(url: String,context: Context) : Observable<Bitmap>
}