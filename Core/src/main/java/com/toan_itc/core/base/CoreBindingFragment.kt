package com.toan_itc.core.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toan_itc.core.base.widget.AutoClearedValue
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by Toan.IT on 12/2/17.
 * Email:Huynhvantoan.itc@gmail.com
 */
abstract class CoreBindingFragment<VM : BaseViewModel, DB : ViewDataBinding> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: DB
        private set
    lateinit var viewModel: VM
        private set

    abstract fun setupViewModel(): VM
    abstract fun inflateBindingLayout(inflater: LayoutInflater): DB
    abstract fun initData()
    abstract fun initView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = setupViewModel()
        //binding = setupBinding(inflater)
        binding = AutoClearedValue(this, setupBinding(inflater)).get()!!
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater): DB {
        val binding = inflateBindingLayout(inflater)
        binding.setVariable(BR.view, this)
        binding.setVariable(BR.viewModel, viewModel)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }
}