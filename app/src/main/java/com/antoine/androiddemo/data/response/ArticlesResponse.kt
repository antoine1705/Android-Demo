package com.antoine.androiddemo.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ArticlesResponse{
    @SerializedName("articles")
    val articles: List<ArticleResponse>? = null
}