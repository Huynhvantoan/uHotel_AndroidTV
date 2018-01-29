package com.toan_itc.core.base.widget

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import com.toan_itc.core.R

class ToolbarIndicator(@param:DrawableRes @field:DrawableRes
                       val drawableRes: Int, val isHomeEnabled: Boolean, val isHomeAsUpEnabled: Boolean) {


    fun getTintedDrawable(toolbar: Toolbar): Drawable? {
        val color = getThemeTintColor(toolbar.context)
        val drawable = ContextCompat.getDrawable(toolbar.context, drawableRes)
        drawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    companion object {
        val NONE = ToolbarIndicator(0, false, false)
        val BACK = ToolbarIndicator(0, true, true)
        val CLOSE = ToolbarIndicator(R.drawable.ic_close, true, true)


        private fun getThemeTintColor(context: Context): Int {
            val attr: Int
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                attr = android.R.attr.colorControlNormal
            } else {
                attr = context.resources.getIdentifier("colorControlNormal", "attr", context.packageName)
            }

            return getColorValueOfAttribute(context, attr)
        }


        private fun getColorValueOfAttribute(context: Context, attr: Int): Int {
            val typedArray = context.obtainStyledAttributes(null, intArrayOf(attr), 0, 0)
            val value = typedArray.getColor(0, 0)
            typedArray.recycle()
            return value
        }
    }
}
