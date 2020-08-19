@file:Suppress("SpellCheckingInspection")

package com.baiyu.dependencies_config.dependencies

/**
 * @author Flywith24
 * @date   2020/5/28
 * time   9:43
 * description
 * third party dependencies
 */
object ThirdParty {
    const val materialiconlib = "net.steamcrafted:materialiconlib:1.1.5"
    const val permission = "com.yanzhenjie.permission:x:2.0.1"

    object Glide {
        private const val glide_version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$glide_version"
        const val compiler = "com.github.bumptech.glide:compiler:$glide_version"
    }

    const val roundedImageView = "com.makeramen:roundedimageview:2.3.0"
    const val blankjUtils = "com.blankj:utilcodex:1.26.0"
    const val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"
    const val Timber = "com.jakewharton.timber:timber:4.7.1"
    const val MMKV = "com.tencent:mmkv:1.1.2"
    const val loadingButton="br.com.simplepass:loading-button-android:2.2.0"

}