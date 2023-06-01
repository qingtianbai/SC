package com.qtbai.sc_test.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.qtbai.sc_test.adapter.ResultListAdapter
import com.qtbai.sc_test.base.mvvm.v.BaseFrameActivity
import com.qtbai.sc_test.databinding.ActivityResultBinding
import com.qtbai.sc_test.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseFrameActivity<ActivityResultBinding, ResultViewModel>() {
    private var mAdapter = ResultListAdapter()

    override fun ActivityResultBinding.initView() {
        initRecycleView()
        intent.getStringExtra("result")?.let { mViewModel.dealData(it) }

    }

    private fun initRecycleView() {
        val manager = LinearLayoutManager(this)
        rv_content.layoutManager = manager
        rv_content.adapter = mAdapter
    }

    override fun initObserve() {
        mViewModel.dataList.observe(this) {
            mAdapter.setData(it)
        }
    }

    override fun initRequestData() {
    }


}
