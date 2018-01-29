package com.toan_itc.core.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Toan.IT on 12/01/17.
 * Email:Huynhvantoan.itc@gmail.com
 */
abstract class CoreFragment : Fragment() {
    private var mContentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == mContentView) {
            mContentView = inflater.inflate(setLayoutResourceID(), container, false)
        }
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    protected abstract fun initViews()

    @LayoutRes
    protected abstract fun setLayoutResourceID(): Int

    protected abstract fun initData()
}
