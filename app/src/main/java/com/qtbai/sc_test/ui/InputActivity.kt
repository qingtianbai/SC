package com.qtbai.sc_test.ui

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.qtbai.sc_test.base.mvvm.v.BaseFrameActivity
import com.qtbai.sc_test.databinding.ActivityInputBinding
import com.qtbai.sc_test.viewmodel.InputViewModel
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : BaseFrameActivity<ActivityInputBinding, InputViewModel>() {

    override fun ActivityInputBinding.initView() {
        etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    val input = p0.toString()
                    input.toDoubleOrNull()?.let { mViewModel.transformAmount(it) }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    val input = p0.toString()
                    input.toDoubleOrNull()?.let { mViewModel.transformtime(it) }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        tvSubmit.setOnClickListener {
            if (etAmount.text != null && etTime.text != null) {
                etTime.text.toString().toDoubleOrNull()?.let { it1 ->
                    etAmount.text.toString().toDoubleOrNull()?.let { it2 ->
                        mViewModel.calculationResult(
                            it2,
                            it1
                        )
                    }
                }
            }
        }

    }

    override fun initObserve() {
        mViewModel.amountDealStr.observe(this) {
            tv_amount_result.text = it
        }
        mViewModel.timeDealStr.observe(this) {
            tv_time_result.text = it
        }
        mViewModel.calculateResult.observe(this) {
            val intent = Intent(
                this,
                ResultActivity::class.java
            )
            intent.putExtra("result", it)
            startActivity(intent)
        }
    }

    override fun initRequestData() {
    }

}
