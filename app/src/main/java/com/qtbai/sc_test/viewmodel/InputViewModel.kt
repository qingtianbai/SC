package com.qtbai.sc_test.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.qtbai.sc_test.repository.InputRepository
import com.qtbai.sc_test.base.mvvm.vm.BaseViewModel
import com.qtbai.sc_test.format
import java.text.DecimalFormat

class InputViewModel(context: Context) : BaseViewModel<InputRepository>(context) {
    val amountDealStr = MutableLiveData<String>()
    val timeDealStr = MutableLiveData<String>()
    val calculateResult = MutableLiveData<String>()

    fun transformAmount(amount: Double) {
        amountDealStr.postValue(amount.format)
    }

    fun transformtime(time: Double) {
        timeDealStr.postValue("${time.toInt() / 60} m${time.toInt() % 60} s")
    }

    fun calculationResult(amount: Double, time: Double) {
        calculateResult.postValue((amount * time).toInt().toString())
    }

    override fun initRepository() = InputRepository()

    private fun formatDouble(double: Double): String {
        val f = DecimalFormat()
        f.applyPattern("#,##0.00")
        return f.format(double)
    }
}