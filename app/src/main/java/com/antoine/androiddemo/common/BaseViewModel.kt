package com.antoine.androiddemo.common

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.antoine.androiddemo.domain.model.ArticleModel
import com.antoine.androiddemo.domain.model.ErrorModel
import com.antoine.androiddemo.util.SingleLiveData
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val mCompositeDisposable = CompositeDisposable()

    val mLoadingObs = SingleLiveData<Boolean>()

    val mErrorObs = SingleLiveData<ErrorModel>()

    fun initialize(arguments: Bundle?) {}

    internal fun handleLoading(): ObservableTransformer<List<ArticleModel>, List<ArticleModel>> {
        return ObservableTransformer {
            it.doOnSubscribe {
                mLoadingObs.postValue(true)
            }.doOnComplete {
                mLoadingObs.postValue(false)
            }
        }
    }

    internal fun handleError(): ObservableTransformer<List<ArticleModel>, List<ArticleModel>> {
        return ObservableTransformer {
            it.doOnError { mErrorObs.postValue(ErrorModel(1, "")) }
        }
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}