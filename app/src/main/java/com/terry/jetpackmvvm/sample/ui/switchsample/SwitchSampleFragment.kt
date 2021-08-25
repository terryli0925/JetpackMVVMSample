package com.terry.jetpackmvvm.sample.ui.switchsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.terry.jetpackmvvm.sample.databinding.FragmentSwitchSampleBinding
import com.terry.jetpackmvvm.sample.ui.MainViewModel

class SwitchSampleFragment : Fragment() {
    private lateinit var binding: FragmentSwitchSampleBinding
    private val viewModel: MainViewModel by activityViewModels()

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}