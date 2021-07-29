package com.example.jetpackmvvm.sample

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.jetpackmvvm.sample.databinding.ActivityMainBinding
import com.example.jetpackmvvm.sample.util.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.setOnClickListener { view ->
//            viewModel.setBannerState(mViewModel.getBannerState().getValue() != null && !mViewModel.getBannerState().getValue());
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_repo, R.id.nav_switch_sample, R.id.nav_slideshow)
                .setOpenableLayout(binding.drawerLayout)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.showBanner.observe(this, { state: Boolean -> binding.appBarMain.fab.visibility = if (state) View.VISIBLE else View.GONE })

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
}