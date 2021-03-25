package com.antoine.androiddemo.domain.model

import android.os.Parcelable
import com.antoine.androiddemo.data.response.ArticleResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,
    val detail: String? = null
) : Parcelable {
    constructor(response: ArticleResponse)
            : this(response.title, response.description, response.image, response.detail)
}