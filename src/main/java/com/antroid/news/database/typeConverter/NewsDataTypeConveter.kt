package com.antroid.news.database.typeConverter

import androidx.room.TypeConverter
import com.antroid.news.database.NewsDataTable
import com.antroid.news.model.TypeAttributes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NewsDataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<NewsDataTable>{
        if(data.isNullOrEmpty()){
            return emptyList()
        }
        val listType = object : TypeToken<NewsDataTable>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObject: List<NewsDataTable>): String {
        return gson.toJson(someObject)
    }
}

object AttributesTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): TypeAttributes {
        if (data.isNullOrEmpty()) {
            return TypeAttributes()
        }
        val objectType = object : TypeToken<TypeAttributes>(){}.type
        return gson.fromJson(data, objectType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObject: TypeAttributes): String {
        return AttributesTypeConverter.gson.toJson(someObject)
    }
}

