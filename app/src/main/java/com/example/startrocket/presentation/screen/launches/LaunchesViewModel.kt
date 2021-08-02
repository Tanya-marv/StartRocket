package com.example.startrocket.presentation.screen.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startrocket.data.repository.LaunchRepository
import com.example.startrocket.domain.Launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(
    private val repository: LaunchRepository
) : ViewModel() {

    private var retrieveLaunchJob: Job? = null
    private var deleteJob: Job? = null

    private val _errorFlow = MutableSharedFlow<String>()

    val launchesFlow = repository.pastLaunches
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        retrieveLaunchCoroutines()
    }

    fun delete(launch: Launch) {
        deleteJob = viewModelScope.launch {
            repository.delete(launch)
        }
    }

    private fun retrieveLaunchCoroutines() {
        retrieveLaunchJob = viewModelScope.launch {
            repository.retrievePastLaunches()
        }
    }

    fun cancel() {
        retrieveLaunchJob?.cancel()
        deleteJob?.cancel()
    }
}