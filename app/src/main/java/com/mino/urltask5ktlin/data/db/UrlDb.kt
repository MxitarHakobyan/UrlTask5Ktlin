package com.mino.urltask5ktlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mino.urltask5ktlin.data.db.dao.UrlDao
import com.mino.urltask5ktlin.data.db.entity.UrlEntity

@Database(entities = [UrlEntity::class], version = 1, exportSchema = false)
abstract class UrlDb : RoomDatabase() {

    abstract fun getUrlDao(): UrlDao
}