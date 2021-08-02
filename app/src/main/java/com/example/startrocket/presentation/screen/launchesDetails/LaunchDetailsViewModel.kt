package com.example.startrocket.presentation.screen.launchesDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startrocket.data.repository.LaunchRepository
import com.example.startrocket.domain.Launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LaunchDetailsViewModel(
    private val repository: LaunchRepository,
    launchId: String
) : ViewModel() {

    private var launchJob: Job? = null

    private val _launchDetailsFlow = MutableStateFlow<Launch?>(null)

    val launchDetailsFlow = _launchDetailsFlow.filterNotNull()

    init {
        retrieveLaunchDetails(launchId)
    }

    private fun retrieveLaunchDetails(id: String) {
        launchJob = viewModelScope.launch {
            val launch = repository.retrievePastLaunchDetailsById(id)
            _launchDetailsFlow.emit(launch)
        }
    }

    fun cancel() {
        launchJob?.cancel()
    }
}