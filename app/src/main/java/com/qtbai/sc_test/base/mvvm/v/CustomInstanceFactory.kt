package com.qtbai.sc_test.base.mvvm.v

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class CustomInstanceFactory(var context: Context?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(Context::class.java).newInstance(context)
            } catch (e: Exception) {
                e.printStackTrace()
                throw IllegalStateException("Cannot create an instance of $modelClass without constructor(context: Context)", e)
            }
        }
        throw IllegalStateException("Cannot create an instance of $modelClass without constructor(context: Context)")
    }
}