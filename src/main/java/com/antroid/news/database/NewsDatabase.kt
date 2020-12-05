package com.antroid.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_DATABASE_NAME
import com.antroid.news.database.typeConverter.AttributesTypeConverter
import com.antroid.news.database.typeConverter.NewsDataTypeConverter

@Database(entities = [NewsDataTable::class], version = 1, exportSchema = false )
@TypeConverters(NewsDataTypeConverter::class, AttributesTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDataDao(): NewsDataDao
    companion object{
        private val LOCK = Any()
        @Volatile private var instance: NewsDatabase? = null
        fun getInstance(context: Context): NewsDatabase? {
            if(instance == null){
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        NEWS_DATABASE_NAME).build()
                }
            }
            return instance
        }
    }
}