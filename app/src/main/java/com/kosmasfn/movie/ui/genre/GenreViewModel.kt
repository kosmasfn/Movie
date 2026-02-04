package com.kosmasfn.movie.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.usecase.UseCase
import com.kosmasfn.movie.ui.mapper.toUIModel
import com.kosmasfn.movie.ui.model.GenreUIModel
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

    val _genres = MutableStateFlow(GenreUIModel())
    val genres: StateFlow<GenreUIModel> = _genres

    fun fetchGenres() {
        viewModelScope.launch {
            userCase.fetchGenres().collect {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _isLoading.value = true
                    }

                    Resource.Status.SUCCESS -> {
                        it.data?.let { data ->
                            _genres.value = data.toUIModel()
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
}