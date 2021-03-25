package com.antoine.androiddemo.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ArticleResponse{

    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("image")
    val image: String? = null

    @SerializedName("detail")
    val detail: String? = null
}

