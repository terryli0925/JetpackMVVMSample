package com.terry.jetpackmvvm.sample.ui.repo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.databinding.FragmentRepoBinding
import com.terry.jetpackmvvm.sample.util.Event
import com.terry.jetpackmvvm.sample.util.ViewModelFactory
import com.terry.jetpackmvvm.sample.util.ViewModelFactory2


class RepoFragment : Fragment() {
    private lateinit var binding: FragmentRepoBinding

    private val viewModel: RepoViewModel by viewModels()
    // 使用ViewModelFactory帶入construct參數
//    private val viewModel: RepoViewModel by viewModels { ViewModelFactory(RepoDataModel()) }
//    private val viewModel2: RepoViewModel by viewModels {
//        ViewModelFactory2 {
//            RepoViewModel(
//                RepoDataModel()
//            )
//        }
//    }
    private val repoAdapter = RepoAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.btnSearch.setOnClickListener { doSearch() }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerView.adapter = repoAdapter
        viewModel.repos.observe(viewLifecycleOwner, Observer<Event<List<Repo>>> { event ->
            val data: List<Repo> = event.content
            repoAdapter.swapItems(data)
            viewModel.setLoading(false)
        })
        return binding.root
    }

    private fun doSearch() {
        val query = binding.edtQuery.text.toString()
        viewModel.setLoading(true)
        viewModel.searchRepo(query)
        dismissKeyboard()
    }

    private fun dismissKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}