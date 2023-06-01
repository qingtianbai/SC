package com.qtbai.sc_test.base.mvvm.v

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @Class: BaseFrameActivity
 * @Remark: Activity base
 * Created by baiqingtian .
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : ViewModel> :
    BaseBindingActivity<VB>() {

    protected val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        val vmClass: Class<VM> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        ViewModelProvider(this, CustomInstanceFactory(this)).get(vmClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserve()
    }

}