package com.baiyu.basearchitecture.databind

import androidx.databinding.ObservableField
/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
class IntObservableField(value: Int = 0) : ObservableField<Int>(value) {

    override fun get(): Int {
        return super.get()!!
    }

}