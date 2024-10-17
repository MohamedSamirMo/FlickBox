package com.example.movieapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.models.BelongsToCollection
import com.example.movieapp.models.DetailsModel
import com.example.movieapp.models.Genre
import com.example.movieapp.models.MovieModel
import com.example.movieapp.models.MovieResult


@Database(entities = [MovieResult::class, DetailsModel::class, BelongsToCollection::class, Genre::class], version = 5  , exportSchema = false)
@TypeConverters(Converts::class)
abstract class MyDatabase: RoomDatabase()  {
    abstract fun getDao(): MyDao


    // before using dagger hilt with roomdb
//    companion object {
//        lateinit var myDatabase: MyDatabase
//        fun init(context: Context){
//            myDatabase =Room.databaseBuilder(context, MyDatabase::class.java
//                ,"myDatabase")
////                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }}

    }


