package com.qtbai.sc_test.base.adapter

import android.view.View
import android.view.ViewGroup

class ItemViewDelegateManager<T> {

    private val itemMap = mutableMapOf<Int, ItemViewDelegate<T>>()
    private var defaultItemType = Int.MAX_VALUE

    fun getItemViewType(t: T, position: Int): Int {
        itemMap.forEach {
            if (it.value.isForViewType(t, position)) {
                return it.key
            }
        }
        return defaultItemType
    }

    fun setDefaultView(delegate: ItemViewDelegate<T>) {
        itemMap[defaultItemType] = delegate
    }

    fun addItemViewMap(viewType: Int, delegate: ItemViewDelegate<T>) {
        if (itemMap.containsKey(viewType)) {
            throw IllegalArgumentException("the type $viewType is exits, pls check out")
        }
        itemMap[viewType] = delegate
    }

    fun addItemViewMap(itemMap: MutableMap<Int, ItemViewDelegate<T>>) {
        itemMap.putAll(itemMap)
    }

    fun onCreateView(parent: ViewGroup, viewType: Int): View {
        return when {
            itemMap.contains(viewType) -> itemMap[viewType]!!.getView(parent)
            itemMap.contains(defaultItemType) -> itemMap[defaultItemType]!!.getView(parent)
            else -> throw IllegalStateException("don't have layout with viewType $viewType, and also have not set defaultViewType")
        }
    }

    fun bindViewHolder(
        holder: BaseViewHolder,
        data: T,
        position: Int,
        payloads: MutableList<T> = arrayListOf()
    ) {
        if (itemMap.contains(holder.itemViewType)) {
            itemMap[holder.itemViewType]!!.bindViewHolder(holder, data, position, payloads)
        }
    }
}