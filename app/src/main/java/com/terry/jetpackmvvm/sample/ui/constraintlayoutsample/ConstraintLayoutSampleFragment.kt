package com.terry.jetpackmvvm.sample.ui.constraintlayoutsample

import android.view.View
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentConstraintLayoutSampleBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment

class ConstraintLayoutSampleFragment : BaseFragment<FragmentConstraintLayoutSampleBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_constraint_layout_sample

    override fun init() {
        binding.tvTitle3.setOnClickListener {
            binding.tvLeft3.visibility =
                if (binding.tvLeft3.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}