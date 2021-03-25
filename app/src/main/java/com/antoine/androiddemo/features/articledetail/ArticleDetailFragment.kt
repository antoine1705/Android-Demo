package com.antoine.androiddemo.features.articledetail

import androidx.fragment.app.viewModels
import com.antoine.androiddemo.R
import com.antoine.androiddemo.common.BaseFragment
import com.antoine.androiddemo.domain.model.ArticleModel
import com.antoine.androiddemo.util.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article_detail.*

@AndroidEntryPoint
class ArticleDetailFragment : BaseFragment<ArticleDetailViewModel>() {

    override val mViewModel: ArticleDetailViewModel by viewModels()

    override fun getLayoutRes() = R.layout.fragment_article_detail

    override fun setupUI() {
        super.setupUI()
        arguments?.getParcelable<ArticleModel>(Constants.Keys.ARTICLE_DETAIL)?.let {
            tvArticleDetail.text = it.detail
            Glide.with(requireContext())
                .load(it.image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivArticleDetailIcon)
        }
    }
}