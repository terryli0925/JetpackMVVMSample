package com.terry.jetpackmvvm.sample.ui.constraintlayoutsample

import android.view.View
import com.terry.jetpackmvvm.sample.databinding.FragmentConstraintLayoutSampleBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment

class ConstraintLayoutSampleFragment : BaseFragment<FragmentConstraintLayoutSampleBinding>(FragmentConstraintLayoutSampleBinding::inflate) {

    override fun initInject() {
        getFragmentComponent().inject(this)
    }

    override fun init() {
        binding.tvTitle3.setOnClickListener {
            binding.tvLeft3.visibility =
                if (binding.tvLeft3.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}