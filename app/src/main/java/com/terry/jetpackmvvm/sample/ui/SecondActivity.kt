package com.terry.jetpackmvvm.sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.terry.jetpackmvvm.sample.MainApplication
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.di.component.DaggerActivityComponent
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent
            .builder()
            .appComponent((application as MainApplication).appComponent)
            .build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}