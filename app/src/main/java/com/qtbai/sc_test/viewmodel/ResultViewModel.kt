package com.qtbai.sc_test.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.qtbai.sc_test.repository.InputRepository
import com.qtbai.sc_test.base.mvvm.vm.BaseViewModel
import com.qtbai.sc_test.bean.ResultData

class ResultViewModel(context: Context) : BaseViewModel<InputRepository>(context) {
    val dataList = MutableLiveData<ArrayList<ResultData>>()

    fun dealData(amount: String) {
        val list = arrayListOf<ResultData>()
        val data = ResultData()
        data.result = amount

        list.add(data)
        list.add(data)
        list.add(data)
        dataList.postValue(list)
    }


    override fun initRepository() = InputRepository()
}