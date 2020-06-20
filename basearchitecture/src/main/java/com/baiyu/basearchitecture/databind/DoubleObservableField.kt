package com.baiyu.basearchitecture.databind

import androidx.databinding.ObservableField
/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
class DoubleObservableField(value: Double = 0.0) : ObservableField<Double>(value) {

    override fun get(): Double {
        return super.get()!!
    }

}