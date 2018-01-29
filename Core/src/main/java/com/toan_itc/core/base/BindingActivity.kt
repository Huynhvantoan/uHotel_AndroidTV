package com.toan_itc.core.base

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Toan.IT on 12/01/17.
 * Email:Huynhvantoan.itc@gmail.com
 */
abstract class BindingActivity<VM : BaseViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity(), BaseView {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: DB

    protected lateinit var viewModel: VM

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): Class<VM>

    abstract fun addObserver() : LifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())!!
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        lifecycle.addObserver(addObserver())
    }
}
