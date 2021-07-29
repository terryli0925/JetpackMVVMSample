package com.example.jetpackmvvm.sample.ui.switchsample

import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackmvvm.sample.databinding.FragmentSwitchSampleBinding
import com.example.jetpackmvvm.sample.util.MainViewModel

class SwitchSampleFragment : Fragment() {
    private lateinit var binding: FragmentSwitchSampleBinding
    private lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
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