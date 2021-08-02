package com.example.startrocket.presentation.screen.launches

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.startrocket.R
import com.example.startrocket.presentation.MainNavigator
import com.example.startrocket.presentation.screen.adapters.MainAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment(R.layout.fragment_launches) {

    private lateinit var launchesRecycler: RecyclerView
    private lateinit var navigator: MainNavigator

    private val viewModel by viewModel<LaunchesViewModel>()
    private val launchesAdapter = MainAdapter(
        onClick = { launch ->
                  navigator.openLaunchDetails(launchId = launch.id)
        },
        onLongClick = { launch ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.madb_title))
                .setMessage(getString(R.string.madb_message, launch.name))
                .setPositiveButton(getString(R.string.madb_positive_btn)) { _, _->
                    viewModel.delete(launch)
                }
                .setNegativeButton(getString(R.string.madb_negative_btn), null)
                .show()
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as? MainNavigator
            ?: throw IllegalStateException(
                "${parentFragment ?: context} must implement ${MainNavigator::class.java.name}"
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectFlow()
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancel()
    }

    private fun collectFlow() {
        viewModel.launchesFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach(launchesAdapter::submitList)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.errorFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach{ error -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show() }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupViews() {
        setupLaunchesRecyclerView()
    }

    private fun setupLaunchesRecyclerView() {
        launchesRecycler = requireView().findViewById(R.id.rv_launches)
        launchesRecycler.apply {
            adapter = launchesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}