package com.kosmasfn.movie.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.usecase.UseCase
import com.kosmasfn.movie.ui.mapper.toUIModel
import com.kosmasfn.movie.ui.model.ReviewUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val userCase: UseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: Flow<String> = _errorMessage

    private val _trailerId = MutableStateFlow("")
    val trailerId: Flow<String> = _trailerId

    private val _isLoadingReviews = MutableStateFlow(false)
    val isLoadingReviews: Flow<Boolean> = _isLoadingReviews

    private val _errorMessageReviews = MutableStateFlow("")
    val errorMessageReviews: Flow<String> = _errorMessageReviews

    val _reviews = MutableStateFlow<List<ReviewUIModel.ReviewItemUIModel>>(emptyList())
    val reviews: StateFlow<List<ReviewUIModel.ReviewItemUIModel>> = _reviews

    private val _totalPages = MutableStateFlow(Integer.MAX_VALUE)
    val totalPages: Flow<Int> = _totalPages

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

    fun fetchReviews(movieId: Int, page: Int) {
        viewModelScope.launch {
            userCase.fetchReviews(movieId, page).collect {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _isLoadingReviews.value = true
                    }

                    Resource.Status.SUCCESS -> {
                        it.data?.let { items ->
                            if (page == 1) _reviews.value = emptyList()
                            _reviews.value += items.results.map { data -> data.toUIModel() }
                            _totalPages.value = items.totalPages
                        }
                        _isLoadingReviews.value = false
                    }

                    Resource.Status.ERROR -> {
                        _isLoadingReviews.value = false
                        _errorMessageReviews.value = it.error?.data?.message ?: "Unknown Error"
                    }
                }
            }
        }
    }
}