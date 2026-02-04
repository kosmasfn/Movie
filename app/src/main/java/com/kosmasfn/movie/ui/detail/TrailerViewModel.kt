package com.kosmasfn.movie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(
    private val userCase: UseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: Flow<String> = _errorMessage

    private val _trailerId = MutableStateFlow("")
    val trailerId: Flow<String> = _trailerId

    fun fetchTrailerMovie(movieId: Int) {
        viewModelScope.launch {
            userCase.fetchTrailerMovie(movieId).collect {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _isLoading.value = true
                    }

                    Resource.Status.SUCCESS -> {
                        it.data?.let { items ->
                            items.results.filter { result -> result.type == "Trailer" }
                                .map { data ->
                                    _trailerId.value = data.key
                                }
                        }
                        _isLoading.value = false
                    }

                    Resource.Status.ERROR -> {
                        _isLoading.value = false
                        _errorMessage.value = it.error?.data?.message ?: "Unknown Error"
                    }
                }
            }
        }
    }
}