package com.terry.jetpackmvvm.sample.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.util.UIUtils

class SpinnerAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_spinner, mutableListOf()) {

    private var selectedItemPos = -1

    override fun convert(holder: BaseViewHolder, item: String) {
        val tv: TextView = holder.getView(R.id.tv_title)
        tv.text = item
        if (holder.layoutPosition == selectedItemPos) {
            tv.setTextColor(UIUtils.getColor(context, R.color.spinner_selected_text))
            tv.setBackgroundResource(R.color.spinner_selected_bg)
        } else {
            tv.setTextColor(UIUtils.getColor(context, R.color.spinner_unselected_text))
            tv.setBackgroundResource(R.color.spinner_unselected_bg)
        }
    }

    override fun setNewInstance(list: MutableList<String>?) {
        selectedItemPos = -1
        super.setNewInstance(list)
    }

    fun setSelectedItemPos(position: Int) {
        selectedItemPos = position
        notifyDataSetChanged()
    }
}

