package com.terry.jetpackmvvm.sample.util

import java.util.regex.Matcher
import java.util.regex.Pattern

object TextUtils {

    fun isAllNum(str: String): Boolean {
        val pattern: Pattern = Pattern.compile("[0-9]*")
        val matcher: Matcher = pattern.matcher(str)
        return matcher.matches()
    }

    fun isAllChar(str: String): Boolean {
        val chars = str.toCharArray()
        var isPhontic = false
        for (i in chars.indices) {
            isPhontic = chars[i] in 'a'..'z' || chars[i] in 'A'..'Z'
            if (!isPhontic) {
                return false
            }
        }
        return true
    }

    //判断字符串是否只包含字母和数字
    fun isCharOrNum(string: String): Boolean {
        var flag = false
        for (i in string.indices) {
            if (Character.isLowerCase(string[i])
                || Character.isUpperCase(string[i])
                || Character.isDigit(string[i])
            ) {
                flag = true
            } else {
                flag = false
                return flag
            }
        }
        return flag
    }

}