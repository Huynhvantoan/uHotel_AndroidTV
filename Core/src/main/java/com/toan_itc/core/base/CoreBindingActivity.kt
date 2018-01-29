package com.toan_itc.core.base

import android.arch.lifecycle.ViewModelProvider
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater

import com.toan_itc.core.BR
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Toan.IT on 12/1/17.
 * Email:Huynhvantoan.itc@gmail.com
 */

abstract class CoreBindingActivity<VM : BaseViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity(), BaseView {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: VM
        private set
    lateinit var binding: DB
        private set

    abstract fun setupViewModel(): VM
    abstract fun inflateBindingLayout(inflater: LayoutInflater): DB
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = setupViewModel()
        binding = setupBinding(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    private fun setupBinding(inflater: LayoutInflater): DB {
        val binding = inflateBindingLayout(inflater)
        binding.setVariable(BR.view, this)
        binding.setVariable(BR.viewModel, viewModel)
        return binding
    }
}