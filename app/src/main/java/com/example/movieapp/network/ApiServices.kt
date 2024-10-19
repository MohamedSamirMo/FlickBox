package com.example.movieapp.network

import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.MovieModel
import com.example.movieapp.models.MovieResult

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    // Retrofit with Coroutines
    // الحصول على الأفلام مع دعم pagination عبر تمرير رقم الصفحة
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieModel
@GET("search/movie")
suspend fun searchMovies(@Query("query") query: String): MovieModel
//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieResult
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): MovieResult
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): DetailsModel



    // جلب الأفلام الأكثر شهرة
    @GET("movie/popular")
    suspend fun getPopularMovies(): List<MovieResult>?

    // جلب الأفلام ذات التقييم الأعلى
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): List<MovieResult>?

    // جلب الأفلام التي تُعرض حالياً
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): List<MovieResult>?


}
// Retrofit with Rxjava
//@GET("posts")
//fun getPosts(): Single<List<PostModelX>>
//
//
//@GET("comments")
//fun getCommentsByPostId(@Query("postId") postId: Int): Single<List<CommentsModel>>