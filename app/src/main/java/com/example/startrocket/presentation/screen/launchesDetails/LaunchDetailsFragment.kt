package com.example.startrocket.presentation.screen.launchesDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.startrocket.R
import com.example.startrocket.domain.Launch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class LaunchDetailsFragment : Fragment(R.layout.fragment_launch_details) {

    private val viewModel by viewModel<LaunchDetailsViewModel> {
        parametersOf(arguments?.getString(ARG_LAUNCH_ID))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlow()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancel()
    }

    private fun collectFlow() {
        viewModel.launchDetailsFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach(::showLaunch)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showLaunch(launch: Launch) {
        requireView().findViewById<ImageView>(R.id.iv_logo).load(launch.imageUrl)
        requireView().findViewById<TextView>(R.id.tv_name).text = launch.name
        requireView().findViewById<TextView>(R.id.tv_data).text = pattern.format(parser.parse(launch.dateUtc)).toString()
        requireView().findViewById<TextView>(R.id.tv_details).text = launch.details
        if (launch.webcast != null) {
            requireView().findViewById<Button>(R.id.btn_video).setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(launch.webcast))
                startActivity(i)
            }
        } else {
            requireView().findViewById<Button>(R.id.btn_video).isVisible = false
        }
    }

    companion object {

        val pattern = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        private const val ARG_LAUNCH_ID = "ARG_LAUNCH_ID"

        fun newInstance(launchId: String) = LaunchDetailsFragment().apply {
            arguments = bundleOf(
                ARG_LAUNCH_ID to launchId
            )
        }
    }
}