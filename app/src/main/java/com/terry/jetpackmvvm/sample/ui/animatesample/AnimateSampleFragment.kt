package com.terry.jetpackmvvm.sample.ui.animatesample

import android.animation.ValueAnimator
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import com.terry.jetpackmvvm.sample.databinding.FragmentAnimateSampleBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.util.ScreenUtils

class AnimateSampleFragment : BaseFragment<FragmentAnimateSampleBinding>(
    FragmentAnimateSampleBinding::inflate
), View.OnClickListener {

    private val animateSampleViewModel: AnimateSampleViewModel by viewModels()

    private lateinit var valueAnimator1: ValueAnimator

    override fun init() {
        initView()
    }

    private fun initView() {
        binding.vOneLeft.setOnClickListener(this)
        binding.vOneRight.setOnClickListener(this)
    }

    private fun startValueAnimator1(left: Boolean) {
        val maxHeight = ScreenUtils.px2dp(50)
        valueAnimator1 = ValueAnimator.ofInt(1, maxHeight).apply {
            duration = 1000
            addUpdateListener {
                val showValue = it.animatedValue as Int
                val hideValue =  maxHeight - showValue
                updateOneViewHeight(binding.vOneLeft, if (left) hideValue else showValue)
                updateOneViewHeight(binding.vOneRight, if (!left) hideValue else showValue)
            }
            start()
        }
    }

    private fun updateOneViewHeight(v: View, height: Int) {
        v.visibility = if (height <= 0) View.INVISIBLE else View.VISIBLE

        v.updateLayoutParams {
            this.height = height
        }
    }

    override fun onClick(v: View?) {
        if (v == binding.vOneLeft) {
            startValueAnimator1(true)
        } else if (v == binding.vOneRight) {
            startValueAnimator1(false)
        }
    }

    private fun testANR() {
        val t = 100 / 0
    }

}