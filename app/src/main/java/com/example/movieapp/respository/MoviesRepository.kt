package com.example.movieapp.respository

import com.example.movieapp.local.MyDao
import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.MovieResult
import com.example.movieapp.network.ApiServices
import javax.inject.Inject

class MoviesRepository @Inject constructor(val dao: MyDao, val apiServices: ApiServices) {

    // جلب الأفلام من الشبكة أو قاعدة البيانات
    suspend fun getMovie(): List<MovieResult>? {
        return try {
            // حاول الحصول على الأفلام من الشبكة
            val data = apiServices.getMovies()
            // إدراج الأفلام في قاعدة البيانات
            insertPost(data.results ?: emptyList())
            data.results
        } catch (e: Exception) {
            // في حال وجود استثناء، جلب البيانات من الكاش (قاعدة البيانات المحلية)
            getFromCache()
        }
    }

    // جلب تفاصيل الفيلم من الشبكة أو الكاش
    suspend fun getMovieDetails(movieId: Int): DetailsModel? {
        return try {
            // حاول جلب التفاصيل من الشبكة
            val data = apiServices.getMovieDetails(movieId)
            // إدراج التفاصيل في قاعدة البيانات
            insertMovieDetails(data)
            data
        } catch (e: Exception) {
            // في حال وجود استثناء، جلب البيانات من الكاش
            getDetailsFromCache(movieId)
        }
    }

    // إدراج الأفلام في قاعدة البيانات
    private suspend fun insertPost(list: List<MovieResult>) {
        dao.insertMovie(list)
    }

    // جلب البيانات من الكاش (قاعدة البيانات المحلية)
    suspend fun getFromCache(): List<MovieResult> {
        return dao.getAllMovies()
    }

    // إدراج تفاصيل الفيلم في قاعدة البيانات
    private suspend fun insertMovieDetails(details: DetailsModel) {
        dao.insertDetails(details)
    }

    // جلب تفاصيل الفيلم من الكاش بناءً على ID
    private suspend fun getDetailsFromCache(id: Int): DetailsModel? {
        return dao.getDetailsById(id)
    }
}
