package com.mino.urltask5ktlin.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResponseUtilKtTest {

    private val excepted: Int = URL_UNAVAILABLE

    @Test
    fun isResponseValid() {
        assertEquals(excepted, isResponseValid(300))
    }
}