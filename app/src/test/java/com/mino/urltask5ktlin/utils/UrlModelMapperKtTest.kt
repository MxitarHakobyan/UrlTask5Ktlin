package com.mino.urltask5ktlin.utils

import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.ui.main.viewmodel.UrlModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class UrlModelMapperKtTest {

    private val excepted: MutableList<UrlModel> = arrayListOf()
    private val urlEntities :MutableList<UrlEntity> = arrayListOf()

    @Before
    fun setUp() {

        urlEntities.add(UrlEntity("google", 13, 0))
        urlEntities.add(UrlEntity("google", 13, 0))
        urlEntities.add(UrlEntity("google", 13, 0))

        excepted.add(UrlModel("google", 13))
        excepted.add(UrlModel("google", 13))
        excepted.add(UrlModel("google", 13))
    }

    @Test
    fun convert2UrlModel() {
        assertEquals(excepted.get(1).availability.get(), convert2UrlModel(urlEntities).get(1).availability.get())
    }
}