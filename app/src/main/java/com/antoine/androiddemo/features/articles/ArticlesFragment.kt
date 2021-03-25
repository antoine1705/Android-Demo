package com.antoine.androiddemo.features.articles

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.antoine.androiddemo.R
import com.antoine.androiddemo.R.layout.fragment_articles_list
import com.antoine.androiddemo.common.BaseFragment
import com.antoine.androiddemo.features.articles.adapter.ArticlesAdapter
import com.antoine.androiddemo.util.Constants
import com.antoine.androiddemo.util.extension.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_articles_list.*

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<ArticlesViewModel>() {

    override val mViewModel: ArticlesViewModel by viewModels()

    override fun getLayoutRes() = fragment_articles_list

    private var adapter: ArticlesAdapter? = null

    override fun setupUI() {
        super.setupUI()

        if (adapter == null) {
            adapter = ArticlesAdapter(onItemClickListener)
        }
        rvArticles.adapter = adapter
    }

    private val onItemClickListener = { position: Int ->
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_articlesFragment_to_articleDetailFragment, Bundle().apply {
                putParcelable(
                    Constants.Keys.ARTICLE_DETAIL,
                    mViewModel.getArticleByPosition(position)
                )
            })
    }

    override fun setupViewModel() {
        super.setupViewModel()
        mViewModel.articlesObs.observe(this, {
            adapter?.submitList(it)
        })
    }

    override fun handleLoading(isShow: Boolean) {
        pbLoading.setVisible(isShow)
    }

}