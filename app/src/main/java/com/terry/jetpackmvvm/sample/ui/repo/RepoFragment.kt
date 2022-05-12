package com.terry.jetpackmvvm.sample.ui.repo

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.jetpackmvvm.sample.databinding.FragmentRepoBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.util.NetworkUtils


class RepoFragment : BaseFragment<FragmentRepoBinding>(FragmentRepoBinding::inflate) {
    private val viewModel: RepoViewModel by viewModels()
    private val repoAdapter = RepoAdapter(mutableListOf())

    override fun init() {
        binding.btnSearch.setOnClickListener { doSearch() }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerView.adapter = repoAdapter
        viewModel.repos.observe(viewLifecycleOwner) { event ->
            val data = event?.content ?: listOf()
            repoAdapter.swapItems(data)
            viewModel.setLoading(false)
        }
    }

    override fun onResume() {
        super.onResume()
        checkNetworkConnection()
    }

    private fun doSearch() {
        val query = binding.edtQuery.text.toString()
        viewModel.setLoading(true)
        viewModel.searchRepo(query)
        dismissKeyboard()
    }

    private fun checkNetworkConnection() {
        val status = NetworkUtils.isConnected()
        binding.btnSearch.isEnabled = status
        if (!status) Toast.makeText(context, "Connection is disconnected", Toast.LENGTH_SHORT)
            .show()
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