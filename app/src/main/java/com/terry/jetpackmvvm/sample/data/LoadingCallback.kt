package com.terry.jetpackmvvm.sample.data

import com.terry.jetpackmvvm.sample.widget.LoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class LoadingCallback<T>: Callback<T> {

    private var dialog: LoadingDialog = LoadingDialog()

    init {
        LoadingDialog.show()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        LoadingDialog.dismiss()
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        LoadingDialog.dismiss()
    }
}