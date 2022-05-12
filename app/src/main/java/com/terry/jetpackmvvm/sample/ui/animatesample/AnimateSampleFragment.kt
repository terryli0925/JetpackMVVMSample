package com.terry.jetpackmvvm.sample.ui.animatesample

import android.animation.ObjectAnimator
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

    override fun init() {
        initView()
    }

    private fun initView() {
        binding.vVaLeft.setOnClickListener(this)
        binding.vVaRight.setOnClickListener(this)
        binding.tvOa.setOnClickListener(this)
        binding.vOaLeft.setOnClickListener(this)
        binding.vOaRight.setOnClickListener(this)
    }

    private fun startValueAnimator1(left: Boolean) {
        val maxHeight = ScreenUtils.px2dp(50)
        ValueAnimator.ofInt(1, maxHeight).apply {
            duration = 1000
            addUpdateListener {
                val showValue = it.animatedValue as Int
                val hideValue =  maxHeight - showValue
                updateOneViewHeight(binding.vVaLeft, if (left) hideValue else showValue)
                updateOneViewHeight(binding.vVaRight, if (!left) hideValue else showValue)
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

    private fun startObjectAnimator1(left: Boolean) {
        if (left) {
            ObjectAnimator.ofFloat(binding.vOaLeft, "translationX", 100f).apply {
                duration = 1000
                start()
            }
            ObjectAnimator.ofFloat(binding.vOaRight, "alpha", 0f).apply {
                duration = 1000
                start()
            }
        } else {
            ObjectAnimator.ofFloat(binding.vOaLeft, "alpha", 0f).apply {
                duration = 1000
                start()
            }
            ObjectAnimator.ofFloat(binding.vOaRight, "translationX", -100f).apply {
                duration = 1000
                start()
            }
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.vVaLeft -> {
                startValueAnimator1(true)
            }
            binding.vVaRight -> {
                startValueAnimator1(false)
            }
            binding.tvOa -> {
                //reset
                binding.vOaLeft.translationX = 0f
                binding.vOaLeft.alpha = 1f
                binding.vOaRight.translationX = 0f
                binding.vOaRight.alpha = 1f
            }
            binding.vOaLeft -> {
                startObjectAnimator1(true)
            }
            binding.vOaRight -> {
                startObjectAnimator1(false)
            }
        }
    }
}