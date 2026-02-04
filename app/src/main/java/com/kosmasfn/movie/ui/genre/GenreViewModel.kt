package com.kosmasfn.movie.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.usecase.UseCase
import com.kosmasfn.movie.ui.mapper.toUIModel
import com.kosmasfn.movie.ui.model.GenreUIModel
import com.kosmasfn.movie.ui.model.MovieUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class GenreViewModel @Inject constructor(
    private val userCase: UseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: Flow<String> = _errorMessage

    val _genre = MutableStateFlow(GenreUIModel())
    val genre: StateFlow<GenreUIModel> = _genre

    val _movies = MutableStateFlow<List<MovieUIModel.MovieItemUIModel>>(emptyList())
    val movies: StateFlow<List<MovieUIModel.MovieItemUIModel>> = _movies

    private val _totalPages = MutableStateFlow(Integer.MAX_VALUE)
    val totalPages: Flow<Int> = _totalPages

    fun fetchGenres() {
        viewModelScope.launch {
            userCase.fetchGenres().collect {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _isLoading.value = true
                    }

                    Resource.Status.SUCCESS -> {
                        it.data?.let { data ->
                            _genre.value = data.toUIModel()
                        }
                        _isLoading.value = false
                    }

                    Resource.Status.ERROR -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun fetchMoviesByGenre(page: Int, genre: String) {
        viewModelScope.launch {
            userCase.fetchMoviesByGenre(page, genre).collect {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _isLoading.value = true
                    }

                    Resource.Status.SUCCESS -> {
                        it.data?.let { items ->
                            if (page == 1) _movies.value = emptyList()
                            _movies.value += items.results.map { data -> data.toUIModel() }
                            _totalPages.value = items.totalPages
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