package com.example.corona_app.ui.provinces

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_app.R
import kotlinx.android.synthetic.main.daftar_item_prov.view.*

class ProvincesAdapter:RecyclerView.Adapter<ProvincesAdapter.ProvincesHolder>() {
    private val mData = ArrayList<ProvincesData>()
    fun setData(items: ArrayList<ProvincesData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    inner class ProvincesHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(provincesData: ProvincesData) {
            with(itemView){
                item_prov.text = provincesData.province
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvincesHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_item_prov, parent, false)
        return ProvincesHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ProvincesHolder, position: Int) {
        holder.bind(mData[position])
        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val provinceDetail = Intent(mContext, DetailProvince::class.java)
            provinceDetail.putExtra(DetailProvince.EXTRA_DATA, mData[position])
            mContext.startActivity(provinceDetail)
        }
    }
}