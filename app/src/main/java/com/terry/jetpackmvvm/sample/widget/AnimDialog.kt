package com.terry.jetpackmvvm.sample.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.terry.jetpackmvvm.sample.R


/**
 * ex:
 *         BaseAnimDialog dialog = new BaseAnimDialog(this);
 *         xxBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.xx, null, false);
 *         dialog.contentView(binding.getRoot())
 *                 .dimAmount(0.7f)
 *                 .gravity(Gravity.BOTTOM)
 *                 .animType(BaseDialog.AnimInType.BOTTOM)
 *                 .layoutParams(new ViewGroup.LayoutParams((ScreenSupportUtils.getScreenWidth() * 1), ViewGroup.LayoutParams.WRAP_CONTENT))
 *                 .setOnOutsideTouchEventListener(event -> {
 *                     verifyPhoneInput(binding, binding.et.getText().toString(), true);
 *                     SystemUtil.INSTANCE.hideSoftInput(NewsActivity.this, binding.getRoot());
 *                 })
 *                 .canceledOnTouchOutside(false)
 *                 .show();
 */
class AnimDialog: Dialog {

    private var onOutsideTouchListener: OnOutsideTouchEventListener? = null

    enum class AnimInType(val type: Int) {
        ZOOM(0), TOP(1), BOTTOM(2);
    }

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除对话框的标题
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun contentView(@LayoutRes layoutResID: Int): AnimDialog {
        window?.setContentView(layoutResID)
        return this
    }

    fun contentView(view: View): AnimDialog {
        window?.setContentView(view)
        return this
    }

    fun contentView(view: View, params: ViewGroup.LayoutParams?): AnimDialog {
        window?.setContentView(view, params)
        return this
    }

    fun layoutParams(params: ViewGroup.LayoutParams): AnimDialog {
        window?.setLayout(params.width, params.height)
        return this
    }

    fun setOnOutsideTouchEventListener(listener: OnOutsideTouchEventListener): AnimDialog {
        onOutsideTouchListener = listener
        return this
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (onOutsideTouchListener != null && event.action == MotionEvent.ACTION_DOWN) {
            // create a rect for storing the window rect
            val r = Rect(0, 0, 0, 0)
            // retrieve the windows rect
            window!!.decorView.getHitRect(r)
            // check if the event position is inside the window rect
            // if the event is not inside then we can close the activity
            if (!r.contains(event.x.toInt(), event.y.toInt())) {
                onOutsideTouchListener!!.onOutsideTouchEvent(event)
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 点击外面是否能dissmiss
     */
    fun cancelable(flag: Boolean): AnimDialog {
        setCancelable(flag)
        return this
    }

    /**
     * 位置
     */

    fun gravity(gravity: Int): AnimDialog {
        window?.setGravity(gravity)
        return this
    }

    /**
     * 偏移
     */
    fun offset(x: Int, y: Int): AnimDialog {
        window?.attributes?.x = x
        window?.attributes?.y = y
        return this
    }

    /**
     * 设置背景阴影,必须setContentView之后调用才生效
     */
    fun dimAmount(dimAmount: Float): AnimDialog {
        window?.attributes?.dimAmount = dimAmount
        return this
    }

    fun animType(animInType: AnimInType): AnimDialog {
        when (animInType.type) {
            AnimInType.ZOOM.type -> window?.setWindowAnimations(R.style.dialog_zoom)
            AnimInType.TOP.type -> window?.setWindowAnimations(R.style.dialog_anim_top)
            AnimInType.BOTTOM.type -> window?.setWindowAnimations(R.style.dialog_anim_bottom)
        }
        return this
    }

    interface OnOutsideTouchEventListener {
        fun onOutsideTouchEvent(event: MotionEvent?)
    }
}