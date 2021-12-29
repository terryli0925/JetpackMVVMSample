package com.terry.jetpackmvvm.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.terry.jetpackmvvm.sample.MainApplication
import com.terry.jetpackmvvm.sample.di.component.ActivityComponent
import com.terry.jetpackmvvm.sample.di.component.DaggerActivityComponent
import com.terry.jetpackmvvm.sample.di.component.DaggerFragmentComponent
import com.terry.jetpackmvvm.sample.di.component.FragmentComponent

abstract class BaseActivity<VB : ViewDataBinding>(private val inflateMethod: (LayoutInflater) -> VB) :
        AppCompatActivity() {

    protected var bundle: Bundle? = null
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        initInject()
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        _binding = inflateMethod.invoke(layoutInflater)
        setContentView(binding.root)

        init()
    }

    protected fun buildActivityComponent(): ActivityComponent {
        return DaggerActivityComponent
            .builder()
            .appComponent((application as MainApplication).appComponent)
            .build()
    }

    protected abstract fun initInject()

    /**
     * Initial
     */
    protected abstract fun init()
}