package com.narender.nytimes

import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Assert.*

@RunWith(JUnit4::class)
class ResultActivityViewModelTest {

    @Mock
    private val resultResponseApi: ResultResponseApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getArticleSuccess() {
        val list = TestDataGenerator.getResultList()
        Mockito.`when`(resultResponseApi.getPosts("7", BuildConfig.API_KEY))
                .thenReturn(list)
        assert(list.byline != null)
    }

    @Test
    fun getArticleError() {
        val list = TestDataGenerator.getResultList()[14]
        Mockito.`when`(resultResponseApi.getPosts("14", BuildConfig.API_KEY))
                .thenReturn(list)
        Assert.assertNull(list)
    }
}
