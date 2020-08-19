package com.baiyu.jetpackexercise.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.baiyu.basearchitecture.base.BaseDataBindingActivity
import com.baiyu.basearchitecture.widget.SlackLoadingView
import com.baiyu.jetpackexercise.BR
import com.baiyu.jetpackexercise.R
import com.baiyu.jetpackexercise.widget.LoadingDialog


/**
 * @author Baiyu
 * @date :2020/6/13 4:12 PM June
 * @version: 1.0
 */
abstract class BaseActivity : BaseDataBindingActivity() {

    lateinit var mViewModel: BaseViewModel

    private var dialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel()
        viewBinding().run {
            setVariable(BR.vm,mViewModel)
        }
        initData()
        registerObserver()
        registerUIChange()

    }

    abstract fun initData()


    /**
     * 创建观察者
     */
    abstract fun registerObserver()


    abstract fun viewModel(): BaseViewModel

    abstract fun viewBinding(): ViewDataBinding

    /**
     * 打开等待框
     */
    @SuppressLint("InflateParams")
    protected fun showLoading(message: String) {
        if (dialog==null){
            val view: View = layoutInflater.inflate(R.layout.dialog_loading, null)
            dialog = LoadingDialog(this,  view, R.style.MyDialogStyle)
            dialog?.setCancelable(false)
            val slackLoadingView=view.findViewById<SlackLoadingView>(R.id.loading)
            slackLoadingView.start()
        }
        dialog?.show()

    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.dismiss()
    }

    /**
     * 注册 UI 事件
     */
    private fun registerUIChange() {
        mViewModel.defUI.showDialog.observe(this, Observer {
            showLoading(
                if (it.isEmpty()) {
                    "请求网络中..."
                } else it
            )
        })
        mViewModel.defUI.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }
}