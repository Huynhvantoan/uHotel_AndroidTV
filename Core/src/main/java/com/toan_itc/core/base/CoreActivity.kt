package com.toan_itc.core.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import com.toan_itc.core.R
import com.toan_itc.core.base.widget.ToolbarIndicator
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Toan.IT on 12/01/17.
 * Email:Huynhvantoan.itc@gmail.com
 */
abstract class CoreActivity : DaggerAppCompatActivity() {
    private var mToolbarHashCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutResourceID())
        initFragment()
        initViews()
        initData()
    }

    /**
     * Setup main toolbar as ActionBar. Try to tint navigation icon based on toolbar's theme.
     *
     * @param indicator navigation icon (NONE, BACK, CLOSE are predefined). Uses toolbar theme color for tinting.
     * @param title     to be shown as in ActionBar. If it is null, title is not changed! Use empty string to clear it.
     * @param toolbar   may be null, in that case it is looking for R.id.toolbar.
     * @return initialized ActionBar or null
     */
    @JvmOverloads
    fun setupActionBar(indicator: ToolbarIndicator, title: CharSequence? = null, toolbar: Toolbar? = null): ActionBar? {
        var mToolbar = toolbar
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar)
            if (mToolbar == null) {
                throw IllegalStateException("Toolbar not found. Add Toolbar with R.id.toolbar identifier in the activity layout or pass Toolbar as a parameter.")
            }
        }

        // this check is here because if 2 fragments with different indicators share a toolbar in activity,
        // it caused bug that back icon was not shown
        if (mToolbarHashCode != mToolbar.hashCode()) {
            setSupportActionBar(mToolbar)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            actionBar.setDisplayUseLogoEnabled(false)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(indicator.isHomeAsUpEnabled)
            actionBar.setHomeButtonEnabled(indicator.isHomeEnabled)

            if (indicator.drawableRes == 0) {
                actionBar.setHomeAsUpIndicator(null)
            } else {
                val iconDrawable = indicator.getTintedDrawable(mToolbar)
                actionBar.setHomeAsUpIndicator(iconDrawable)
            }

            if (title != null) {
                actionBar.title = title
            }
        }
        mToolbarHashCode = mToolbar.hashCode()
        return actionBar
    }

    protected abstract fun initFragment()

    protected abstract fun initViews()

    @LayoutRes
    protected abstract fun setLayoutResourceID(): Int

    protected abstract fun initData()

}
