package com.qtbai.sc_test.base.adapter

interface OnItemClick<T> {

    fun onItemClick(data: T, position: Int)

}