package com.antoine.androiddemo.common

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    abstract val mViewModel: T?

    abstract fun getLayoutRes(): Int

    open fun setupUI() = Unit

    open fun setupViewModel() = Unit

    open fun handleLoading(isShow: Boolean) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.initialize(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        mViewModel?.mLoadingObs?.observe(this, {
            handleLoading(it)
        })
        mViewModel?.mErrorObs?.observe(this, {
            AlertDialog.Builder(requireContext())
                .setMessage(it.errorMsg)
        })
    }
}