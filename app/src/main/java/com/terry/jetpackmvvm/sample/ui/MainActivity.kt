package com.terry.jetpackmvvm.sample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.terry.jetpackmvvm.sample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.terry.jetpackmvvm.sample.MainApplication
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.util.TAG
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager
import com.terry.jetpackmvvm.sample.util.activitymanager.OnAppStatusListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.setOnClickListener { view ->
//            viewModel.setBannerState(mViewModel.getBannerState().getValue() != null && !mViewModel.getBannerState().getValue());
            //startActivity(Intent(this, RepoViewModel::class.java))
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            startActivity(Intent(this, SecondActivity::class.java))
        }
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_repo,
            R.id.nav_switch_sample,
            R.id.nav_slideshow,
            R.id.nav_constraint_layout_sample
        )
            .setOpenableLayout(binding.drawerLayout)
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)
        viewModel.showBanner.observe(
            this,
            { state: Boolean ->
                binding.appBarMain.fab.visibility = if (state) View.VISIBLE else View.GONE
            })

        ActivityManager.callbacks.setOnAppStatusListener(object : OnAppStatusListener {
            override fun onFront() {
                Log.i(TAG, "onFront")
                Log.i("TAG", "onFront")
            }

            override fun onBack() {
                Log.i(TAG, "onBack")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }

//    companion object {
//        public const val TAG = MainActivity::class.qualifiedName
//    }
}