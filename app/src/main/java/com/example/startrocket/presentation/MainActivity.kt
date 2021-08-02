package com.example.startrocket.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.startrocket.R
import com.example.startrocket.presentation.screen.launches.LaunchesFragment
import com.example.startrocket.presentation.screen.launchesDetails.LaunchDetailsFragment

class MainActivity : AppCompatActivity(), MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openLaunchesScreen()
        }
    }

    private fun openLaunchesScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, LaunchesFragment())
            .commit()
    }

    override fun openLaunchDetails(launchId: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, LaunchDetailsFragment.newInstance(launchId))
            .addToBackStack(null)
            .commit()
    }
}