package com.terry.jetpackmvvm.sample.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.terry.jetpackmvvm.sample.R


class SlideshowFragment : Fragment() {
    private val slideshowViewModel: SlideshowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView = root.findViewById<TextView>(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, {
                s -> textView.text = s
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //testANR()
        ScrollingFragment().show(childFragmentManager, "")
    }

    private fun testANR() {
        val t = 100 /0
    }
}