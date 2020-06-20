package com.baiyu.basearchitecture.databind

import androidx.databinding.ObservableField
/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
class StringObservableField(value: String = "") : ObservableField<String>(value) {

    override fun get(): String {
        return super.get()!!
    }

}