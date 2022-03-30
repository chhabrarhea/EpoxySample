package com.rhea.epoxy.simple

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhea.epoxy.api.Repository
import com.rhea.epoxy.api.Resource
import com.rhea.epoxy.models.Movie
import com.rhea.epoxy.models.RewardHeaderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _movieResponse = MutableLiveData<Resource<List<Movie>>>()
    val movieResponse: LiveData<Resource<List<Movie>>>
        get() = _movieResponse

    fun getMovies() {
        viewModelScope.launch {
            _movieResponse.postValue(repo.getSimpleMovieList())
        }
    }

    fun getHeaderValues() = RewardHeaderModel(73270, 36230)
}