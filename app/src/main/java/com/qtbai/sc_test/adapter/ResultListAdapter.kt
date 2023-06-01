package com.qtbai.sc_test.adapter

import com.qtbai.sc_test.R
import com.qtbai.sc_test.base.adapter.BaseRecyclerViewAdapter
import com.qtbai.sc_test.base.adapter.BaseViewHolder
import com.qtbai.sc_test.bean.ResultData
import kotlinx.android.synthetic.main.item_result_list.view.*


class ResultListAdapter : BaseRecyclerViewAdapter<ResultData>(R.layout.item_result_list) {
    override fun convert(
        holder: BaseViewHolder,
        data: ResultData,
        position: Int,
        payloads: MutableList<ResultData>
    ) {
        super.convert(holder, data, position, payloads)
        holder.itemView.tv_content.text = data.result
    }
    fun setData(arrayList: ArrayList<ResultData>) {
        mData.clear()
        add(arrayList)
    }
}