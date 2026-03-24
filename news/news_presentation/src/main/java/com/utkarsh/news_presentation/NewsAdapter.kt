package com.utkarsh.news_presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.utkarsh.news_domain.model.Article
import com.utkarsh.news_presentation.databinding.ViewHolderArticlesBinding

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var list= listOf<Article>()

    fun setData(list:List<Article>){
        this.list=list
        notifyItemInserted(this.list.lastIndex)
    }
    inner class MyViewHolder(val viewDataBinding:ViewHolderArticlesBinding):RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val binding=ViewHolderArticlesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        holder.viewDataBinding.apply {
            val item=list[position]
            item.urlToImage?.let { ivArticle.loadImage(it) }?:"https://static.vecteezy.com/system/resources/thumbnails/057/068/323/small/single-fresh-red-strawberry-on-table-green-background-food-fruit-sweet-macro-juicy-plant-image-photo.jpg"
            tvHeadlines.text=item.title
            tvContent.text=item.content
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    fun ImageView.loadImage(url:String){
        val circularProgressDrawable=CircularProgressDrawable(this.context)
        circularProgressDrawable.strokeWidth=5f
        circularProgressDrawable.centerRadius=30f
        circularProgressDrawable.start()
        Glide.with(this).load(url).placeholder(circularProgressDrawable)
            .error(com.google.android.material.R.drawable.mtrl_ic_error).into(this)
    }
}