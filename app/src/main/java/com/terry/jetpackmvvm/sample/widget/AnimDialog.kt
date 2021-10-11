package com.terry.jetpackmvvm.sample.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
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

    enum class AnimInType(val type: Int) {
        CENTER_ZOOM(0), TOP(1), BOTTOM(2);
    }

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
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

    fun animType(animInType: AnimInType): AnimDialog {
        when (animInType.type) {
            AnimInType.CENTER_ZOOM.type -> window?.setWindowAnimations(R.style.dialog_zoom)
            AnimInType.TOP.type -> window?.setWindowAnimations(R.style.dialog_anim_top)
            AnimInType.BOTTOM.type -> window?.setWindowAnimations(R.style.dialog_anim_bottom)
        }
        return this
    }
}