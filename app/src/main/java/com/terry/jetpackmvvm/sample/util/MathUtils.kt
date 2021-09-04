package com.terry.jetpackmvvm.sample.util

import java.math.BigDecimal
import java.text.DecimalFormat

object MathUtils {

    fun currencyFormat(amount: String): String {
        val formatter = DecimalFormat("#,##0.00")
        return formatter.format(amount.toDouble())
    }

    /**
     * 提供精确的加法運算
     */
    fun add(v1: Double, v2: Double): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.add(b2).toDouble()
    }
}