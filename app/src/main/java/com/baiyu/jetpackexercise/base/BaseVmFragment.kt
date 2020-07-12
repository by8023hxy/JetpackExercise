package com.baiyu.jetpackexercise.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author Baiyu
 * @date :2020/7/12 7:37 PM July
 * @version: 1.0
 */
abstract class BaseVmFragment  : Fragment(){

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(inflater,container,savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createObserve()
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) :View

    abstract fun initView()
    abstract fun initData()
    abstract fun createObserve()

}