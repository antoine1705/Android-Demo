package com.antoine.androiddemo.data.api

import com.antoine.androiddemo.data.response.ArticlesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface  AppService {

    @GET("/example-feed/feed.json")
    fun getArticles(): Observable<ArticlesResponse>
}