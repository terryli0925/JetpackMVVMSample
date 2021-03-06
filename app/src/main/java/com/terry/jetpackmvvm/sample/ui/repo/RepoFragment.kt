package com.terry.jetpackmvvm.sample.ui.repo

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.databinding.FragmentRepoBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.util.Event


class RepoFragment : BaseFragment<FragmentRepoBinding>(FragmentRepoBinding::inflate) {
    private val viewModel: RepoViewModel by viewModels()
    private val repoAdapter = RepoAdapter(mutableListOf())

    override fun init() {
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