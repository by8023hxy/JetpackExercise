package com.baiyu.jetpackexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.baiyu.jetpackexercise.BR
import com.baiyu.jetpackexercise.R
import com.baiyu.jetpackexercise.base.BaseVmFragment
import com.baiyu.jetpackexercise.databinding.FragmentHomeBinding

/**
 * @author Baiyu
 * @date :2020/7/12 10:59 AM July
 * @version: 1.0
 */
class HomeFragment : BaseVmFragment(){

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home,container).apply {
           setVariable(BR.vm,homeViewModel)
           homeViewModel.apply {
               getList()
           }
           lifecycleOwner = this@HomeFragment
       }.root
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun createObserve() {

    }


}