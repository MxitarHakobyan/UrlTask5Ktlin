package com.mino.urltask5ktlin.utils

import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.ui.main.viewmodel.UrlModel

fun convert2UrlModel(urlEntities: List<UrlEntity>): List<UrlModel> {
    val urlModels: MutableList<UrlModel> = arrayListOf()
    for (urlEntity in urlEntities) {
        urlModels.add(
            UrlModel(
                urlEntity.url,
                urlEntity.availability
            )
        )
    }
    return urlModels
}

