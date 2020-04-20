package com.example.corona_app.ui.country

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corona_app.R
import kotlinx.android.synthetic.main.daftar_item.view.*

class CountryAdapter:RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    private val mData = ArrayList<CountryData>()
    fun setData(items: ArrayList<CountryData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    inner class CountryHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale")
        fun bind(country:CountryData){
            with(itemView){
                var value = country.country.toLowerCase()
                var out = value.replace(" ", "-")
                Glide.with(itemView.context)
                    .load("https://assets.thebasetrip.com/api/v2/countries/flags/${out}.png")
                    .dontAnimate()
                    .into(img_item_poster)
                item_negara.text = country.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_item, parent, false)
        return CountryHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(mData[position])
        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val countryDetail = Intent(mContext, DetailCountry::class.java)
            countryDetail.putExtra(DetailCountry.EXTRA_DATA, mData[position])
            mContext.startActivity(countryDetail)
        }
    }
}

