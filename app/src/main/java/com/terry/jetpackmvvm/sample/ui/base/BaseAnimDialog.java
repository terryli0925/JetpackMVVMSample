package com.terry.jetpackmvvm.sample.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.terry.jetpackmvvm.sample.R;

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
public class BaseAnimDialog extends Dialog {

    private OnOutsideTouchEventListener mListener;

    public BaseAnimDialog(Context context) {
        this(context, 0);
    }

    public BaseAnimDialog(Context context, int themeResId) {
        super(context, themeResId);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除对话框的标题
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(0x00000000);
//        getWindow().setBackgroundDrawable(gradientDrawable);//设置对话框边框背景,必须在代码中设置对话框背景，不然对话框背景是黑色的
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dimAmount(0.2f);
    }

    public interface OnOutsideTouchEventListener {
        void onOutsideTouchEvent(MotionEvent event);
    }

    public BaseAnimDialog contentView(@LayoutRes int layoutResID) {
        getWindow().setContentView(layoutResID);
        return this;
    }

    public BaseAnimDialog contentView(@NonNull View view) {
        getWindow().setContentView(view);
        return this;
    }

    public BaseAnimDialog contentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        getWindow().setContentView(view, params);
        return this;
    }

    public BaseAnimDialog layoutParams(@Nullable ViewGroup.LayoutParams params) {
        getWindow().setLayout(params.width, params.height);
        return this;
    }

    public BaseAnimDialog setOnOutsideTouchEventListener(OnOutsideTouchEventListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public boolean onTouchEvent(@androidx.annotation.NonNull MotionEvent event) {
        if (mListener != null && event.getAction() == MotionEvent.ACTION_DOWN) {
            // create a rect for storing the window rect
            Rect r = new Rect(0, 0, 0, 0);
            // retrieve the windows rect
            getWindow().getDecorView().getHitRect(r);
            // check if the event position is inside the window rect
            // if the event is not inside then we can close the activity
            if (!r.contains((int) event.getX(), (int) event.getY())) {
                mListener.onOutsideTouchEvent(event);
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 点击外面是否能dissmiss
     *
     * @param canceledOnTouchOutside
     * @return
     */
    public BaseAnimDialog canceledOnTouchOutside(boolean canceledOnTouchOutside) {
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    /**
     * 位置
     *
     * @param gravity
     * @return
     */
    public BaseAnimDialog gravity(int gravity) {

        getWindow().setGravity(gravity);

        return this;

    }

    /**
     * 偏移
     *
     * @param x
     * @param y
     * @return
     */
    public BaseAnimDialog offset(int x, int y) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = x;
        layoutParams.y = y;

        return this;
    }

    /*
       设置背景阴影,必须setContentView之后调用才生效
        */
    public BaseAnimDialog dimAmount(float dimAmount) {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        return this;
    }


    /*
   动画类型
    */
    public BaseAnimDialog animType(BaseAnimDialog.AnimInType animInType) {


        switch (animInType.getIntType()) {
            case 0:
                getWindow().setWindowAnimations(R.style.dialog_zoom);

                break;
            case 2:
                getWindow().setWindowAnimations(R.style.dialog_anim_top);

                break;
            case 4:
                getWindow().setWindowAnimations(R.style.dialog_anim_bottom);

                break;
        }
        return this;
    }


    /*
    动画类型
     */
    public enum AnimInType {
        CENTER(0),
        TOP(2),
        BOTTOM(4);

        AnimInType(int n) {
            intType = n;
        }

        final int intType;

        public int getIntType() {
            return intType;
        }
    }
}
