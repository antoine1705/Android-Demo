package com.antoine.androiddemo.data.repo

import com.antoine.androiddemo.data.api.AppService
import com.antoine.androiddemo.domain.model.ArticleModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArticlesRepository @Inject constructor (private val appService: AppService) {

    fun getArticles() : Observable<List<ArticleModel>>{
        return appService.getArticles()
            .map { it.articles }
            .flatMapIterable { it }
            .map { ArticleModel(it) }
            .toList()
            .toObservable()
    }
}