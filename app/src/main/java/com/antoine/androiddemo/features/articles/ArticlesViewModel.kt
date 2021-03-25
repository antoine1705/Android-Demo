package com.antoine.androiddemo.features.articles

import androidx.lifecycle.MutableLiveData
import com.antoine.androiddemo.common.BaseViewModel
import com.antoine.androiddemo.data.repo.ArticlesRepository
import com.antoine.androiddemo.domain.model.ArticleModel
import com.antoine.androiddemo.util.extension.add
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(articlesRepository: ArticlesRepository) :
    BaseViewModel() {

    val articlesObs = MutableLiveData<List<ArticleModel>>()

    init {
        articlesRepository.getArticles()
            .compose(handleLoading())
            .subscribe {
                articlesObs.postValue(it)
            }.add(this)
    }

    fun getArticleByPosition(position: Int): ArticleModel? {
        return articlesObs.value?.getOrNull(position)
    }
}
