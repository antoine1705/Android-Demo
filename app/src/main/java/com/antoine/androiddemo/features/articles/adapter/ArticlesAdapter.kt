package com.antoine.androiddemo.features.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antoine.androiddemo.R
import com.antoine.androiddemo.domain.model.ArticleModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*

class ArticlesAdapter(private val mListener: (Int) -> Unit) :
    ListAdapter<ArticleModel, ArticlesAdapter.ArticleViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            mListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    class ArticleViewHolder(mListener: (Int) -> Unit, private val view: View) :
        RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                (it.tag as? Int)?.apply { mListener.invoke(this) }
            }
        }

        fun bind(position: Int, item: ArticleModel) = with(view) {
            tag = position
            this.tvTitle.text = item.title
            this.tvDescription.text = item.description
            Glide.with(context)
                .load(item.image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(this.ivIcon)
        }
    }
}

internal class DiffCallBack : DiffUtil.ItemCallback<ArticleModel>() {
    override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem == newItem
    }
}