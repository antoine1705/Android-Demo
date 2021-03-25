package com.antoine.androiddemo.util.extension

import com.antoine.androiddemo.common.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.add(viewModel: BaseViewModel) {
    viewModel.mCompositeDisposable.add(this)
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}