package com.qtbai.sc_test.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 */
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder> {

    private var layoutRes: Int = -1
    var mData: ArrayList<T> = arrayListOf()
    private var isMultiType = false
    private var itemViewDelegateManager = ItemViewDelegateManager<T>()

    constructor(isMultiType:Boolean){
        this.isMultiType = isMultiType
    }

    constructor(layoutRes: Int) {
        this.layoutRes = layoutRes
    }

    constructor(layoutRes: Int, data: ArrayList<T>) {
        this.layoutRes = layoutRes
        mData.addAll(data)
    }

    override fun getItemViewType(position: Int): Int {
        if (isMultiType) {
            return itemViewDelegateManager.getItemViewType(mData[position], position)
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (isMultiType) {
            return BaseViewHolder(parent.context, itemViewDelegateManager.onCreateView(parent, viewType))
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        onViewCreated(view)
        return BaseViewHolder(parent.context, view)
    }

    open fun onViewCreated(view: View){

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        bindViewHolder(holder, mData[position], position, (payloads as? MutableList<T>) ?: arrayListOf())
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun bindViewHolder(
        holder: BaseViewHolder,
        data: T,
        position: Int,
        payloads: MutableList<T> = arrayListOf()
    ) {

        if (isMultiType) {
            itemViewDelegateManager.bindViewHolder(holder, data, position, payloads)
        } else {
            convert(holder, data, position, payloads)
        }
    }

    open fun convert(
        holder: BaseViewHolder,
        data: T,
        position: Int,
        payloads: MutableList<T> = arrayListOf()
    ) {

    }

    fun addItemViewDelegate(viewType: Int, delegate: ItemViewDelegate<T>) {
        isMultiType = true
        itemViewDelegateManager.addItemViewMap(viewType, delegate)
    }

    fun setDefaultView(delegate: ItemViewDelegate<T>) {
        itemViewDelegateManager.setDefaultView(delegate)
    }

    fun addItemViewDelegate(itemMap: MutableMap<Int, ItemViewDelegate<T>>) {
        isMultiType = true
        itemViewDelegateManager.addItemViewMap(itemMap)
    }


    fun add(data: List<T>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

}