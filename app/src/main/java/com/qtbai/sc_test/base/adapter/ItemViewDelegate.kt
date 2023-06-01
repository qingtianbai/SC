package com.qtbai.sc_test.base.adapter

import android.view.View
import android.view.ViewGroup

interface ItemViewDelegate<T> {
    fun isForViewType(value: T, position: Int): Boolean

    fun getView(parent: ViewGroup):View

    fun bindViewHolder(holder: BaseViewHolder,
                       data: T,
                       position: Int,
                       payloads: MutableList<T> = arrayListOf())
}