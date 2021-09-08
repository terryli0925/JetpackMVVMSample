package com.terry.jetpackmvvm.sample.ui.constraintlayoutsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentConstraintLayoutSampleBinding

class ConstraintLayoutSampleFragment : Fragment() {

    private lateinit var binding: FragmentConstraintLayoutSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentConstraintLayoutSampleBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_constraint_layout_sample,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvTitle3.setOnClickListener {
            binding.tvLeft3.visibility =
                if (binding.tvLeft3.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}