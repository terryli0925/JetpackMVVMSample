package com.terry.jetpackmvvm.sample.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

import androidx.viewpager.widget.ViewPager

class NoScrollViewPager : ViewPager {
    //是否禁止左右滑动
    var noScroll = true

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (noScroll) {
            false
        } else super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (noScroll) {
            false
        } else super.onInterceptTouchEvent(ev)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}
