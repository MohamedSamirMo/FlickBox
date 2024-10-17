package com.example.movieapp.local

import androidx.room.TypeConverter
import com.example.movieapp.models.BelongsToCollection
import com.example.movieapp.models.Genre
import com.example.movieapp.models.ProductionCompany
import com.example.movieapp.models.ProductionCountry
import com.example.movieapp.models.SpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converts {
    private val gson=Gson()
    @TypeConverter
    fun fromList(list: List<Int>?):String?{
        return gson.toJson(list)
    }
    @TypeConverter
    fun toList(list: String):List<Int>?{
        val listType=object : TypeToken<List<Int>?>(){}.type
        return gson.fromJson(list,listType)

    }
    @TypeConverter
    fun fromBelongsToCollection(belongsToCollection: BelongsToCollection?): String? {
        return if (belongsToCollection == null) null else Gson().toJson(belongsToCollection)
    }

    @TypeConverter
    fun toBelongsToCollection(belongsToCollectionString: String?): BelongsToCollection? {
        return if (belongsToCollectionString == null) null else Gson().fromJson(belongsToCollectionString, object : TypeToken<BelongsToCollection>() {}.type)
    }
    // Add more type converters for other data types if needed
    @TypeConverter
    fun fromGenres(genres: List<Genre>?): String? {
        return if (genres == null) null else Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenres(genresString: String?): List<Genre>? {
        return if (genresString == null) null else Gson().fromJson(genresString, object : TypeToken<List<Genre>>() {}.type)
    }


    @TypeConverter
    fun fromProductionCompanies(productionCompanies: List<ProductionCompany>?): String? {
        return if (productionCompanies == null) null else Gson().toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompaniesString: String?): List<ProductionCompany>? {
        return if (productionCompaniesString == null) null else Gson().fromJson(productionCompaniesString, object : TypeToken<List<ProductionCompany>>() {}.type)
    }

    @TypeConverter
    fun fromProductionCountries(productionCountries: List<ProductionCountry>?): String? {
        return if (productionCountries == null) null else Gson().toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountries(productionCountriesString: String?): List<ProductionCountry>? {
        return if (productionCountriesString == null) null else Gson().fromJson(productionCountriesString, object : TypeToken<List<ProductionCountry>>() {}.type)
    }

    @TypeConverter
    fun fromSpokenLanguages(spokenLanguages: List<SpokenLanguage>?): String? {
        return if (spokenLanguages == null) null else Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLanguagesString: String?): List<SpokenLanguage>? {
        return if (spokenLanguagesString == null) null else Gson().fromJson(spokenLanguagesString, object : TypeToken<List<SpokenLanguage>>() {}.type)
    }

    // محول لتحويل قائمة من String (لحقل origin_country)
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return if (list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        return if (string == null) null else Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
    }
}