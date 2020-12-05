package com.antroid.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.antroid.news.database.databaseconstants.NewsDataConstants.SELECT_ALL_NEWS_FEED

@Dao
interface NewsDataDao {

    @Insert(onConflict = REPLACE)
    fun insertNewsFeed(newsFeed: List<NewsDataTable>?)

    @Query(SELECT_ALL_NEWS_FEED)
    fun selectAllNewsFeed(): List<NewsDataTable>
}