package com.mino.urltask5ktlin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urlsTable")
data class UrlEntity(

    @PrimaryKey
    var url: String,

    var availability: Int,

    var loadingTime: Int
)