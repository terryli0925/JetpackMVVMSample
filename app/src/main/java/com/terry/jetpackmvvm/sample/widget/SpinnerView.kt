package com.terry.jetpackmvvm.sample.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.widget.PopupWindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.adapter.SpinnerAdapter
import com.terry.jetpackmvvm.sample.util.UIUtils

class SpinnerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), View.OnClickListener {

    @JvmField
    var title: TextView

    @JvmField
    var selectedText: TextView

    @JvmField
    var arrow: ImageView

    var onItemSelectedListener: OnItemSelectedListener? = null
    private var popupWindow: PopupWindow? = null
    private val spinnerAdapter = SpinnerAdapter()
    private var spinnerWidth: Int = 0

    interface OnItemSelectedListener {
        fun onItemSelected(adapter: BaseQuickAdapter<*, *>, view: View, position: Int)
    }

    init {
        inflate(context, R.layout.layout_spinner, this)

        title = findViewById(R.id.tv_title)
        selectedText = findViewById(R.id.tv_selected_text)
        selectedText.setOnClickListener(this)
        selectedText.post { spinnerWidth = selectedText.width }
        arrow = findViewById(R.id.iv_arrow)

        resetText()
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setTitle(@StringRes resid: Int) {
        title.setText(resid)
    }

    private fun setText(text: String) {
        selectedText.text = text
        selectedText.setTextColor(UIUtils.getColor(context, R.color.black))
    }

    fun resetText() {
        selectedText.setText(R.string.spinner_please_select)
        selectedText.setTextColor(UIUtils.getColor(context, R.color.white))
    }

    fun getText(): String {
        return selectedText.text.toString()
    }

    fun setList(data: MutableList<String>) {
        spinnerAdapter.setNewInstance(data)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_selected_text -> {
                show()
            }
        }
    }

    private fun show() {
        if (spinnerWidth == 0) return
        if (popupWindow == null) {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_popup, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = spinnerAdapter

            popupWindow = PopupWindow(view, spinnerWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true)
            popupWindow!!.setOnDismissListener {
                arrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }
            spinnerAdapter.setOnItemClickListener { adapter, view, position ->
                spinnerAdapter.setSelectedItemPos(position)
                setText(spinnerAdapter.data[position])
                popupWindow?.dismiss()
                onItemSelectedListener?.onItemSelected(adapter, view, position)
            }
        }

        arrow.setImageResource(R.drawable.ic_arrow_drop_up)
        PopupWindowCompat.showAsDropDown(popupWindow!!, selectedText, 0, 0, Gravity.START)
    }
}