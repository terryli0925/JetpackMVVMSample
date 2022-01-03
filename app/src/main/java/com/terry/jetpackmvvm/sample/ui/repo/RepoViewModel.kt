package com.terry.jetpackmvvm.sample.ui.repo

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.util.AbsentLiveData
import com.terry.jetpackmvvm.sample.util.Event
import javax.inject.Inject

class RepoViewModel : ViewModel {
    @Inject
    lateinit var repoRepository: RepoRepository
    private val _isLoading = MutableLiveData<Boolean>()
    private val query = MutableLiveData<String>()
    private var _repos: LiveData<Event<List<Repo>>> = Transformations.switchMap(query) { input ->
        if (TextUtils.isEmpty(input)) {
            AbsentLiveData.create()
        } else {
            repoRepository.searchRepo(input)
        }
    }

    val repos: LiveData<Event<List<Repo>>> = _repos
    val isLoading: LiveData<Boolean> = _isLoading

    @Inject
    constructor() {
//        init(RepoRepository())
    }

    constructor(repoRepository: RepoRepository) {
        init(repoRepository)
    }

    private fun init(repoRepository: RepoRepository) {
        this.repoRepository = repoRepository
    }

    fun searchRepo(inputText: String) {
        query.value = inputText
    }

    fun setLoading(value: Boolean) {
        _isLoading.value = value
    }

    override fun onCleared() {
        super.onCleared()
    }
}