package com.example.movieapp.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.MovieResult
import com.example.movieapp.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {

    val _moviesLiveData = MutableLiveData<List<MovieResult>?>()
    val moviesLiveData get() = _moviesLiveData

    val _errorLiveData = MutableLiveData<String>()
    val errorLiveData get() = _errorLiveData

    // تعديل نوع الـ LiveData الخاص بتفاصيل الفيلم
    private val _movieDetailsLiveData = MutableLiveData<DetailsModel?>()
    val movieDetailsLiveData get() = _movieDetailsLiveData

    val videoLiveData = MutableLiveData<String?>()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(IO) {
            try {
                val data = moviesRepository.getMovie()
                _moviesLiveData.postValue(data)
            } catch (e: Exception) {
                _errorLiveData.postValue(e.message)
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(IO) {
            try {
                // جلب تفاصيل الفيلم من المستودع
                val movieDetails = moviesRepository.getMovieDetails(movieId)
                _movieDetailsLiveData.postValue(movieDetails)
            } catch (e: Exception) {
                _errorLiveData.postValue(e.message)
            }
        }
    }
}
