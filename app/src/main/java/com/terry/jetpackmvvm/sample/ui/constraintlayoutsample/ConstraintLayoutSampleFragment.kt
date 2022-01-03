package com.terry.jetpackmvvm.sample.ui.constraintlayoutsample

import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.databinding.FragmentConstraintLayoutSampleBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.ui.repo.RepoViewModel
import com.terry.jetpackmvvm.sample.util.Event
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ConstraintLayoutSampleFragment :
    BaseFragment<FragmentConstraintLayoutSampleBinding>(FragmentConstraintLayoutSampleBinding::inflate) {

    // viewmodel For test
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: RepoViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun init() {
        viewModel.repos.observe(viewLifecycleOwner, Observer<Event<List<Repo>>> { event ->
        })
        binding.tvTitle3.setOnClickListener {
            binding.tvLeft3.visibility =
                if (binding.tvLeft3.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}