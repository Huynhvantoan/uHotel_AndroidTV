package com.toan_itc.core.imageload

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.support.annotation.Nullable
import android.util.Log
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.common.Priority
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.orhanobut.logger.Logger
import com.toan_itc.core.R
import com.toan_itc.core.richutils.toPx
import io.reactivex.Observable


class FrescoImageLoader : ImageLoaderListener {

    override fun loadImage(url: String, simpleDraweeView: SimpleDraweeView?) {
        simpleDraweeView?.setImageURI(url)
    }

    override fun loadController(source: Int, simpleDraweeView: SimpleDraweeView?, width: Float, height: Float) {
        val request = ImageRequestBuilder.newBuilderWithResourceId(source)
                .setProgressiveRenderingEnabled(true)
                .setResizeOptions(ResizeOptions(width.toPx(), height.toPx()))
                .build()
        val controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView?.controller)
                .setImageRequest(request)
                .build()
        simpleDraweeView?.controller = controller
    }

    override fun loadController(url: String, simpleDraweeView: SimpleDraweeView?, width: Int, height: Int, controllerListener: ControllerListener<Any>?) {
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setLocalThumbnailPreviewsEnabled(true)
                .setCacheChoice(ImageRequest.CacheChoice.DEFAULT)
                .setProgressiveRenderingEnabled(true)
                .setImageDecodeOptions(ImageDecodeOptions.defaults())
                .setResizeOptions(ResizeOptions(width.toPx(), height.toPx()))
                .build()
        val controller = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .setAutoPlayAnimations(true)
                .setOldController(simpleDraweeView?.controller)
                .setImageRequest(request)
                .setControllerListener(controllerListener)
                .build()
        simpleDraweeView?.controller = controller
    }

    override fun loadController(url: String, simpleDraweeView: SimpleDraweeView?, width: Float, height: Float) {
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .setResizeOptions(ResizeOptions(width.toPx(), height.toPx()))
                .build()
        val controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView?.controller)
                .setImageRequest(request)
                .build()
        simpleDraweeView?.controller = controller
    }

    override fun loadHierarchy(url: String, simpleDraweeView: SimpleDraweeView) {
        val hierarchy = GenericDraweeHierarchyBuilder(null)
                .setFadeDuration(300)
                .setPlaceholderImage(R.drawable.ic_launcher)
                .setFailureImage(R.drawable.ic_launcher)
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build()
        simpleDraweeView.hierarchy = hierarchy
    }

    override fun loadBitmap(url: String, context: Context): Observable<Bitmap> {
        return Observable.create { subscriber ->
            val imagePipeline = Fresco.getImagePipeline()
            val imageRequest = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(url))
                    .setRequestPriority(Priority.HIGH)
                    .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                    .build()
            val dataSource = imagePipeline.fetchDecodedImage(imageRequest, context)
            try {
                dataSource!!.subscribe(object : BaseBitmapDataSubscriber() {
                    override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage>>?) {
                        Logger.d(dataSource?.failureCause?.message)
                    }

                    public override fun onNewResultImpl(@Nullable bitmap: Bitmap?) {
                        if (bitmap == null) {
                            Log.d("a", "Bitmap data source returned success, but bitmap null.")
                            return
                        }
                        subscriber.onNext(bitmap)
                        subscriber.onComplete()
                    }
                }, CallerThreadExecutor.getInstance())
            } finally {
                dataSource?.close()
            }
        }
    }
}
