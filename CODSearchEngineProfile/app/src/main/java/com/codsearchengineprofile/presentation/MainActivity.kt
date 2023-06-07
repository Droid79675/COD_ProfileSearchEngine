package com.codsearchengineprofile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.codsearchengineprofile.App
import com.codsearchengineprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
//        setupActionBarWithNavController((supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController)
        setupActionBarWithNavController(binding.fragmentContainerView.getFragment<NavHostFragment>().navController)

    }
}
