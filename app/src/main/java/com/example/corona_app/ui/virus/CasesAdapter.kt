package com.example.corona_app.ui.virus

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corona_app.R
import kotlinx.android.synthetic.main.daftar_item_cases.view.*

class CasesAdapter:RecyclerView.Adapter<CasesAdapter.CasesHolder> (){
    private val mData = ArrayList<CasesData>()
    fun setData(items: ArrayList<CasesData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    inner class CasesHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(casesData: CasesData){
            with(itemView){
                if (casesData.gender == "Perempuan"){
                    Glide.with(itemView.context)
                        .load(R.drawable.woman)
                        .dontAnimate()
                        .into(img_item_poster1)
                }
                else{
                    Glide.with(itemView.context)
                        .load(R.drawable.man)
                        .dontAnimate()
                        .into(img_item_poster1)
                }
                item_case.text = casesData.status
                prov.text = casesData.province
                status_negara.text = casesData.negara
                umur.text = "Umur " + casesData.umur.toString() + " Tahun"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_item_cases, parent, false)
        return CasesHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: CasesHolder, position: Int) = holder.bind(mData[position])

}