package com.qtbai.sc_test.base.mvvm.vm

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qtbai.sc_test.base.mvvm.m.BaseRepository

/**
 * @Class: BaseViewModel
 * @Remark: ViewModel
 * Created by baiqingtian .
 *
 */
@SuppressLint("StaticFieldLeak")
abstract class BaseViewModel<R : BaseRepository> : ViewModel {

    var mContext: Context? = null

    constructor(context: Context?) : super() {
        this.mContext = context
    }

    // 加载状态 0-加载中，1-加载成功 ，2-加载失败
    val loadingState = MutableLiveData(0)

    // 加载异常
    val requestError = MutableLiveData<Throwable?>()

    protected val mRepository: R by lazy { initRepository() }

    protected abstract fun initRepository(): R

    override fun onCleared() {
        super.onCleared()
        mContext = null
    }
}