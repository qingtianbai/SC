package com.qtbai.sc_test.base.mvvm.v

import androidx.viewbinding.ViewBinding

/**
 * @Class: FrameView
 * Created by baiqingtian .
 */
internal interface FrameView<VB : ViewBinding> {

    /**
     * init View
     */
    fun VB.initView()

    /**
     * LiveData
     */
    fun initObserve()

    /**
     * interface
     */
    fun initRequestData()
}