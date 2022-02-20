package com.terry.jetpackmvvm.sample.ui.switchsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentSwitchSampleBinding
import com.terry.jetpackmvvm.sample.ui.MainViewModel
import com.terry.jetpackmvvm.sample.util.UIUtils


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
        binding.btnNext.setOnClickListener {
            if (step >= 5) return@setOnClickListener
            onStepChanged(++step)
        }

        onStepChanged(1)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun onStepChanged(step: Int) {
        when (step) {
            1 -> {
                binding.ivStep1.setImageResource(R.drawable.ic_stepper_circle_blue)
            }
            2 -> {
                binding.ivStep1.setImageResource(R.drawable.ic_stepper_circle_checked)
                binding.vLine1.setBackgroundColor(UIUtils.getColor(requireContext(), android.R.color.holo_blue_light))
                binding.ivStep2.setImageResource(R.drawable.ic_stepper_circle_blue)
            }
            3 -> {
                binding.ivStep2.setImageResource(R.drawable.ic_stepper_circle_checked)
                binding.vLine2.setBackgroundColor(UIUtils.getColor(requireContext(), android.R.color.holo_blue_light))
                binding.ivStep3.setImageResource(R.drawable.ic_stepper_circle_blue)
            }
            4 -> {
                binding.ivStep3.setImageResource(R.drawable.ic_stepper_circle_checked)
                binding.vLine3.setBackgroundColor(UIUtils.getColor(requireContext(), android.R.color.holo_blue_light))
                binding.ivStep4.setImageResource(R.drawable.ic_stepper_circle_blue)
            }
            5 -> {
                binding.ivStep4.setImageResource(R.drawable.ic_stepper_circle_checked)
            }
            else -> return
        }
    }
}