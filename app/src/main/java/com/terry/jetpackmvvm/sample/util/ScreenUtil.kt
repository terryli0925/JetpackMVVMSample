package com.terry.jetpackmvvm.sample.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import java.lang.Exception

object ScreenUtil {
    /**
     * dp转px
     */
    fun dp2px(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().getDisplayMetrics()
        )
            .toInt()
    }

    fun px2dp(px: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px.toFloat(),
            Resources.getSystem().getDisplayMetrics()
        )
            .toInt()
    }

//    //获取是否存在NavigationBar
//    public static boolean checkDeviceHasNavigationBar(Context context) {
//        boolean hasNavigationBar = false;
//        Resources rs = context.getResources();
//        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
//        if (id > 0) {
//            hasNavigationBar = rs.getBoolean(id);
//        }
//        try {
//            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
//            Method m = systemPropertiesClass.getMethod("get", String.class);
//            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
//            if ("1".equals(navBarOverride)) {
//                hasNavigationBar = false;
//            } else if ("0".equals(navBarOverride)) {
//                hasNavigationBar = true;
//            }
//        } catch (Exception e) {
//
//        }
//        return hasNavigationBar;
//
//    }

//    //获取底部导航栏高度
//    public static int getNavigationBarHeight(Context context) {
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        int height = resources.getDimensionPixelSize(resourceId);
//        return height;
//    }
//
//    //获取顶部statusBar高度
//    public static int getStatusBarHeight(Context context) {
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//        int height = resources.getDimensionPixelSize(resourceId);
//        return height;
//    }


    //    //获取是否存在NavigationBar
    //    public static boolean checkDeviceHasNavigationBar(Context context) {
    //        boolean hasNavigationBar = false;
    //        Resources rs = context.getResources();
    //        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
    //        if (id > 0) {
    //            hasNavigationBar = rs.getBoolean(id);
    //        }
    //        try {
    //            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
    //            Method m = systemPropertiesClass.getMethod("get", String.class);
    //            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
    //            if ("1".equals(navBarOverride)) {
    //                hasNavigationBar = false;
    //            } else if ("0".equals(navBarOverride)) {
    //                hasNavigationBar = true;
    //            }
    //        } catch (Exception e) {
    //
    //        }
    //        return hasNavigationBar;
    //
    //    }
    //    //获取底部导航栏高度
    //    public static int getNavigationBarHeight(Context context) {
    //        Resources resources = context.getResources();
    //        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
    //        int height = resources.getDimensionPixelSize(resourceId);
    //        return height;
    //    }
    //
    //    //获取顶部statusBar高度
    //    public static int getStatusBarHeight(Context context) {
    //        Resources resources = context.getResources();
    //        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    //        int height = resources.getDimensionPixelSize(resourceId);
    //        return height;
    //    }

    fun getScreenDisplay(): IntArray? {
        val width = getScreenWidth() // 手机屏幕的宽度
        val height = getScreenHeight() // 手机屏幕的高度
        return intArrayOf(width, height)
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }

    @SuppressLint("PrivateApi")
    fun getStatusHeight(context: Context): Int {
        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = clazz.getField("status_bar_height")[`object`].toString().toInt()
            statusHeight = context.getResources().getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }
}