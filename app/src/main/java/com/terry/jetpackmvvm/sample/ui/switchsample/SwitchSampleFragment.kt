package com.terry.jetpackmvvm.sample.ui.switchsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentSwitchSampleBinding
import com.terry.jetpackmvvm.sample.ui.MainViewModel


class SwitchSampleFragment : Fragment() {
    private lateinit var binding: FragmentSwitchSampleBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var step: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSwitchSampleBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
//        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.showBanner.observe(viewLifecycleOwner, { state: Boolean ->
            binding.switch1.isChecked = state
        })
        binding.switch1.setOnCheckedChangeListener { _, isChecked: Boolean ->
            viewModel.setBannerState(
                isChecked
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivStep1.setOnClickListener {
            if (step < 3) step++ else step = 1
            val resId = when(step) {
                1 -> R.drawable.ic_stepper_circle_black
                2 -> R.drawable.ic_stepper_circle_blue
                3 -> R.drawable.ic_stepper_circle_checked
                else -> -1
            }
            if (resId != -1) {
                binding.ivStep1.setImageResource(resId)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}