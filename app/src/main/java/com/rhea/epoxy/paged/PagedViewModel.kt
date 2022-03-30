package com.rhea.epoxy.paged

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rhea.epoxy.api.Repository
import com.rhea.epoxy.models.RewardHeaderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagedViewModel @Inject constructor(
    private val repo: Repository
): ViewModel(){
    val pagedData = repo.getPagedMovieList().cachedIn(viewModelScope)

    fun getHeaderValues() = RewardHeaderModel(73270, 36230)
}