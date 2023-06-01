package com.qtbai.sc_test

import java.text.DecimalFormat

val Double.format
    get() = run {
        val f = DecimalFormat()
        f.applyPattern("#,##0.00")
        f.format(this)
    }