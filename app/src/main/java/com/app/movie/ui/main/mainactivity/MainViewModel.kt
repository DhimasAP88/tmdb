package com.app.movie.ui.main.mainactivity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movie.data.model.Film
import com.app.movie.data.repository.FilmRepository
import com.app.movie.utils.NetworkHelper
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val filmRepository: FilmRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _filmDetail =MutableLiveData<Film>()
    val filmDetail: LiveData<Film> = _filmDetail

    private val _trendingFilm = MutableLiveData<List<Film>>()
    val trendingFilm: LiveData<List<Film>> = _trendingFilm

    private val _discoveryFilm = MutableLiveData<List<Film>>()
    val discoveryFilm : LiveData<List<Film>> = _discoveryFilm

    fun getMovieList(listId: String) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                filmRepository.getListMovie(listId).let {
                    if (it.isSuccessful) {
                        it.body()?.let { list ->
                            _filmDetail.postValue(list.items[0])
                            _trendingFilm.postValue(list.items.take(5))
                            _discoveryFilm.postValue(list.items.takeLast(5))
                        }

                    }
                }
            }
        }
    }
}