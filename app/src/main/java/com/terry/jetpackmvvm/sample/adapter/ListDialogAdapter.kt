package com.terry.jetpackmvvm.sample.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.terry.jetpackmvvm.sample.R

class ListDialogAdapter(layoutResId: Int, data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, content: String) {
        holder.setText(R.id.content, content)
    }
}