package com.mino.urltask5ktlin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import io.reactivex.Flowable

@Dao
interface UrlDao {

    @Insert(onConflict = REPLACE)
    fun insert(urlEntity: UrlEntity)

    @Update
    fun update(urlEntity: UrlEntity)

    @Query("DELETE FROM urlsTable WHERE url = :url")
    fun deleteByUrl(url: String)

    @Query("SELECT * FROM urlsTable ORDER BY url ASC")
    fun getUrlsOrderByUrl(): Flowable<List<UrlEntity>>

    @Query("SELECT * FROM urlsTable ORDER BY availability ASC")
    fun getUrlsOrderByAvailability(): Flowable<List<UrlEntity>>

    @Query("SELECT * FROM urlsTable ORDER BY loadingTime ASC")
    fun getUrlsOrderByLoadingTime(): Flowable<List<UrlEntity>>
}