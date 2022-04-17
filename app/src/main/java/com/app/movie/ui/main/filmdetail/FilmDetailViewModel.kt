package com.app.movie.ui.main.filmdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movie.data.model.Film
import com.app.movie.data.model.Review
import com.app.movie.data.repository.FilmRepository
import com.app.movie.utils.NetworkHelper
import com.app.movie.utils.Resource
import kotlinx.coroutines.launch

class FilmDetailViewModel @ViewModelInject constructor(
    private val filmRepository: FilmRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _filmDetail = MutableLiveData<Resource<Film>>()
    val filmDetail: LiveData<Resource<Film>> = _filmDetail

    private val _filmReview = MutableLiveData<Resource<Review>>()
    val filmReview: LiveData<Resource<Review>> = _filmReview

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            _filmDetail.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                filmRepository.getMovieDetail(movieId).let {
                    if (it.isSuccessful) {
                        _filmDetail.postValue(Resource.success(it.body()))
                        filmRepository.getMovieReview(movieId).let { response ->
                            _filmReview.postValue(Resource.success(response.body()?.results?.get(0)))
                        }
                    }
                }
            }
        }
    }

}