package com.example.jetpackmvvm.sample.ui.repo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvm.sample.data.model.Repo
import com.example.jetpackmvvm.sample.databinding.FragmentRepoBinding
import com.example.jetpackmvvm.sample.util.Event

class RepoFragment : Fragment() {
    private lateinit var binding: FragmentRepoBinding
    private lateinit var viewModel: RepoViewModel
    private val repoAdapter = RepoAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(RepoViewModel::class.java)
        // 使用ViewModelFactory帶入construct參數
//        val factory = ViewModelFactory(RepoViewModel())
//        viewModel = ViewModelProvider(this, factory).get(RepoViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.btnSearch.setOnClickListener { doSearch() }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
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