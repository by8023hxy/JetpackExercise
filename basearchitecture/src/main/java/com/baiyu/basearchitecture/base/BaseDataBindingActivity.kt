package com.baiyu.basearchitecture.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Baiyu
 * @date :2020/6/13 3:06 PM June
 * @version: 1.0
 */
abstract class BaseDataBindingActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }

    //protected inline fun <reified T : ViewModel> viewModel(): Lazy<T> = lazy { ViewModelProvider(this).get(T::class.java) }
}