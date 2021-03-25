package com.antoine.androiddemo.features.articles

import com.antoine.androiddemo.BaseTestCase
import com.antoine.androiddemo.data.repo.ArticlesRepository
import com.antoine.androiddemo.domain.model.ArticleModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArticlesViewModelTest : BaseTestCase() {

    private lateinit var mViewModel: ArticlesViewModel

    @MockK
    lateinit var repo: ArticlesRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `test getArticles() then return empty `() {
        every { repo.getArticles() } returns Observable.just(listOf())

        mViewModel = ArticlesViewModel(repo)
        mViewModel.articlesObs.observeForever {
            Assert.assertTrue(it.isEmpty())
        }
    }

    @Test
    fun `test getArticles() then return 1 item `() {
        val expectItem = ArticleModel("title", "description", "image", "detail")
        val unExpectItem = ArticleModel("title2", "description2", "image2", "detail2")
        every { repo.getArticles() } returns Observable.just(listOf(expectItem))

        mViewModel = ArticlesViewModel(repo)
        mViewModel.articlesObs.observeForever {
            Assert.assertEquals(expectItem, it.firstOrNull())
            Assert.assertNotEquals(unExpectItem, it.firstOrNull())
        }
    }

    @Test
    fun `test getArticleByPosition() then return 1 item `() {
        val expectItem = ArticleModel("title", "description", "image", "detail")
        val expectItem2 = ArticleModel("title2", "description2", "image2", "detail2")
        every { repo.getArticles() } returns Observable.just(listOf(expectItem, expectItem2))

        mViewModel = ArticlesViewModel(repo)
        val actual = mViewModel.getArticleByPosition(0)
        Assert.assertEquals(actual, expectItem)
        Assert.assertNotEquals(actual, expectItem2)

        val actual2 = mViewModel.getArticleByPosition(1)
        Assert.assertNotEquals(actual2, expectItem)
        Assert.assertEquals(actual2, expectItem2)
    }
}