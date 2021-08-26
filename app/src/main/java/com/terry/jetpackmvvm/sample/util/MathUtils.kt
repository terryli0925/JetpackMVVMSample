package com.terry.jetpackmvvm.sample.util

import java.text.DecimalFormat

object MathUtils {

    fun currencyFormat(amount: String): String {
        val formatter = DecimalFormat("#,##0.00")
        return formatter.format(amount.toDouble())
    }
}