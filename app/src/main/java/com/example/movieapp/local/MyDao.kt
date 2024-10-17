package com.example.movieapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.MovieResult

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyDao {
    // Room Database with Coroutines

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(list: List<MovieResult>)
    @Query("select * from MovieResult")
    suspend fun getAllMovies(): List<MovieResult>
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieDetails(list: List<MovieResult>)
//    @Query("select * from MovieResult where id=:id")
//    suspend fun getMovieById(id: Int): MovieResult
    // إدراج تفاصيل الفيلم
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: DetailsModel)

    // استعلام للحصول على تفاصيل الفيلم بناءً على ID
    @Query("SELECT * FROM DetailsModel WHERE id = :id")
    suspend fun getDetailsById(id: Int): DetailsModel?





}

// Room Database with Rxjava
//@Insert
//fun insertPost(list: List<PostModelX>) : Completable
//
//@Query("select * from PostModelX")
//fun getAllPosts(): Single<List<PostModelX>>
//
//@Insert
//fun insertComment(list: List<CommentsModel>): Completable
//
//@Insert
//fun insertComments(list: List<CommentsModel>): Completable
//
//@Query("select * from CommentsModel where postId=:postId")
//fun getCommentsByPostId(postId: Int): Single<List<CommentsModel>>
//

