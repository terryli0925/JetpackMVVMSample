package com.terry.jetpackmvvm.sample.util

import android.graphics.Rect
import android.view.View

object EditTextUtils {
    /**
     * addLayoutListener方法如下
     * @param main 根布局
     * @param scroll 需要显示的最下方View
     */
    fun addLayoutListener(main: View, scroll: View) {
        main.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            //1、获取main在窗体的可视区域
            main.getWindowVisibleDisplayFrame(rect);
            //2、获取main在窗体的不可视区域高度，在键盘没有弹起时，main.getRootView().getHeight()调节度应该和rect.bottom高度一样
            val mainInvisibleHeight = main.rootView.height - rect.bottom
            val screenHeight = main.rootView.height //屏幕高度
            //3、不可见区域大于屏幕本身高度的1/3：说明键盘弹起了
            if (mainInvisibleHeight > screenHeight / 4) {
                val location = intArrayOf(0, 0)
                scroll.getLocationInWindow(location)
                // 4､获取Scroll的窗体坐标，算出main需要滚动的高度
                val scrollHeight = (location[1] + scroll.height) - rect.bottom
                //5､让界面整体上移键盘的高度
                if (scrollHeight > 0) {
                    main.scrollTo(0, scrollHeight)
                }
            } else {
                //3、不可见区域小于屏幕高度1/3时,说明键盘隐藏了，把界面下移，移回到原有高度
                main.scrollTo(0, 0)
            }
        }
    }
}