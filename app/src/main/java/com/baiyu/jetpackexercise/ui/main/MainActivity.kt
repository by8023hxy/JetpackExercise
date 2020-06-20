package com.baiyu.jetpackexercise.ui.main


import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import br.com.simplepass.loadingbutton.animatedDrawables.ProgressType
import com.baiyu.basearchitecture.livedata.observeState
import com.baiyu.jetpackexercise.R
import com.baiyu.jetpackexercise.base.BaseActivity
import com.baiyu.jetpackexercise.base.BaseViewModel
import com.baiyu.jetpackexercise.databinding.ActivityMainBinding
import com.baiyu.jetpackexercise.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun initData() {

        mainViewModel.getTest()
        btn_test.run {
            setOnClickListener {
                progressType=ProgressType.INDETERMINATE
                startAnimation()
                postDelayed({revertAnimation()
                },2000)
            }
        }

    }

    override fun registerObserver() {
        mainViewModel.responseLoginResponse.observeState(this,
            onLoading = {

            }, onSuccess = {

            }, onError = {

            })
    }

    override fun viewModel(): BaseViewModel = mainViewModel
    override fun viewBinding(): ViewDataBinding = binding


}