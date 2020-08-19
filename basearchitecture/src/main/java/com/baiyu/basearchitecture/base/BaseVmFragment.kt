package com.baiyu.basearchitecture.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * @author Baiyu
 * @date :2020/7/12 7:37 PM July
 * @version: 1.0
 */
abstract class BaseVmFragment : Fragment() {

    lateinit var appCompatActivity: AppCompatActivity

    protected inline fun <reified T : ViewDataBinding> binding(
            inflater: LayoutInflater,
            @LayoutRes resId: Int,
            container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appCompatActivity = context as AppCompatActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return createView(inflater, container, savedInstanceState)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createObserve()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun createObserve()

}