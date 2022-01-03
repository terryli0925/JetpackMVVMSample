package com.terry.jetpackmvvm.sample.ui.constraintlayoutsample

import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import com.terry.jetpackmvvm.sample.databinding.FragmentConstraintLayoutSampleBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.ui.repo.RepoViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ConstraintLayoutSampleFragment : BaseFragment<FragmentConstraintLayoutSampleBinding>(FragmentConstraintLayoutSampleBinding::inflate) {

    @Inject
    lateinit var viewModel: RepoViewModel   // For test

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun init() {
        binding.tvTitle3.setOnClickListener {
            binding.tvLeft3.visibility =
                if (binding.tvLeft3.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}