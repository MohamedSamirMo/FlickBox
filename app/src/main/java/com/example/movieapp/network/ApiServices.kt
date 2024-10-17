package com.example.movieapp.network

import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.MovieModel
import com.example.movieapp.models.MovieResult

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    // Retrofit with Coroutines
@GET("discover/movie")
suspend fun getMovies():MovieModel

@GET("search/movie")
suspend fun searchMovies(@Query("query") query: String): MovieModel
//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieResult
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): MovieResult
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): DetailsModel
}
// Retrofit with Rxjava
//@GET("posts")
//fun getPosts(): Single<List<PostModelX>>
//
//
//@GET("comments")
//fun getCommentsByPostId(@Query("postId") postId: Int): Single<List<CommentsModel>>