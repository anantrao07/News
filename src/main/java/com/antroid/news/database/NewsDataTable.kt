package com.antroid.news.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWSDATA_TABLE_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_ATTRIBUTES_COLUMN_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_DESCRIPTION_COLUMN_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_ID_COLUMN_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_READABLE_PUBLISHED_COLUMN_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_TITLE_COLUMN_NAME
import com.antroid.news.database.databaseconstants.DatabaseConstants.NEWS_TYPE_COLUMN_NAME
import com.antroid.news.model.DataItem
import com.antroid.news.model.TypeAttributes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = NEWSDATA_TABLE_NAME)
@Parcelize
class NewsDataTable(): Parcelable, DataItem {
    @PrimaryKey
    @Expose
    @NotNull
    @SerializedName("id")
    @ColumnInfo(name = NEWS_ID_COLUMN_NAME)
    var newsId: Int = 0

    @NotNull
    @Expose
    @ColumnInfo(name = NEWS_DESCRIPTION_COLUMN_NAME)
    var description: String = ""

    @NotNull
    @Expose
    @ColumnInfo(name = NEWS_READABLE_PUBLISHED_COLUMN_NAME)
    var readablePublishedAt: String = ""

    @NotNull
    @Expose
    @ColumnInfo(name = NEWS_TITLE_COLUMN_NAME)
    var title: String = ""

    @NotNull
    @Expose
    @ColumnInfo(name = NEWS_TYPE_COLUMN_NAME)
    var type: String = ""

    @NotNull
    @Expose
    @ColumnInfo(name = NEWS_ATTRIBUTES_COLUMN_NAME)
    var typeAttributes: TypeAttributes = TypeAttributes()

    constructor(newsId: Int, description: String, readablePublishedAt: String, title: String, type: String, typeAttributes: TypeAttributes ) : this() {
        this.newsId = newsId
        this.description = description
        this.readablePublishedAt = readablePublishedAt
        this.title = title
        this.type = type
        this.typeAttributes = typeAttributes
    }

    override fun getId(): Int {
        return 0
    }
}