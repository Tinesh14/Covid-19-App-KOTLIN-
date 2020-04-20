package com.example.corona_app.ui.news

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corona_app.R
import com.example.corona_app.ui.DateTime
import com.example.corona_app.ui.country.DetailCountry
import kotlinx.android.synthetic.main.daftar_item_news.view.*

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsHolder> (){
    private val mData = ArrayList<NewsData>()
    fun setData(items: ArrayList<NewsData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    inner class NewsHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(newsData: NewsData){
            with(itemView){
                Glide.with(itemView.context)
                    .load(newsData.urlImage)
                    .dontAnimate()
                    .into(img)
                author.text = newsData.author
                Log.d(" ",DateTime().buildDate("1585267200000"))
                publishedAt.text = DateTime().DateFormat(newsData.publishedAt)
                source.text = newsData.source
                desc.text = newsData.description
                title.text = newsData.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_item_news, parent, false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(mData[position])
        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val newsDetail = Intent(mContext, NewsDetail::class.java)
            newsDetail.putExtra(NewsDetail.EXTRA_DATA, mData[position])
            mContext.startActivity(newsDetail)
        }
    }
}