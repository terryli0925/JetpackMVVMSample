package com.terry.jetpackmvvm.sample.ui.utiltest.dialog

import androidx.recyclerview.widget.GridLayoutManager
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.adapter.ListDialogAdapter
import com.terry.jetpackmvvm.sample.databinding.FragmentScrollingBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseDialogFragment

class ScrollingFragment : BaseDialogFragment<FragmentScrollingBinding>() {

    override fun onStart() {
        super.onStart()
        setWidthRatio(0.9)

        dialog?.window?.attributes?.let {
            val windowParams = it
            windowParams.dimAmount = 0.9f
            dialog?.window?.attributes = windowParams
        }

        val adapter = ListDialogAdapter(R.layout.item_dialog_list, mutableListOf());
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter

        var list = mutableListOf<String>()
        for (i in 0..10) {
            list.add(i.toString())
        }
        adapter.setNewInstance(list)
    }

    override fun getLayoutId(): Int = R.layout.fragment_scrolling

}