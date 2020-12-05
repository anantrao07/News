package com.antroid.news

import android.content.Context
import androidx.room.Room
import com.antroid.news.database.NewsDatabase
import org.junit.runner.RunWith
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.antroid.news.database.NewsDataDao
import com.antroid.news.database.NewsDataTable
import com.antroid.news.model.TypeAttributes
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var dbTest: NewsDatabase
    private lateinit var newsDao: NewsDataDao

    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbTest = Room.inMemoryDatabaseBuilder(
            context,
            NewsDatabase::class.java
        ).build()
        newsDao = dbTest.newsDataDao()
    }

    @After
    @Throws(IOException::class)
    fun close(){
        dbTest.close()
    }

    @Test
    @Throws(Exception::class)
    fun testWriteAndReadNews(){
        val news = listOf<NewsDataTable>(NewsDataTable(
            1234,
            "Test News Description",
        "4 Dec, 2020",
        "Test News Title",
        "story",
        TypeAttributes()
        ))
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.insertNewsFeed(news)
            val savedNews = newsDao.selectAllNewsFeed()
            assertTrue(savedNews[0].newsId == news[0].newsId)
        }
    }

}