package com.example.corona_app.ui.provinces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_app.R
import kotlinx.android.synthetic.main.fragment_provinces.*

class ProvincesFragment : Fragment() {

    private lateinit var provincesViewModel: ProvincesViewModel
    private lateinit var rvProvince: RecyclerView
    private lateinit var adapter: ProvincesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_provinces, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvProvince = view.findViewById(R.id.rv_provinsi)
        rvProvince.setHasFixedSize(true)
        adapter = ProvincesAdapter()
        adapter.notifyDataSetChanged()
        rvProvince.layoutManager = LinearLayoutManager(activity)
        rvProvince.adapter = adapter

        provincesViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(ProvincesViewModel::class.java)
        provincesViewModel.setProvinces()
        provincesViewModel.getProvinces().observe(viewLifecycleOwner, Observer {items ->
            if(items != null){
                adapter.setData(items)
                showLoading(false)
            }
        })
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar1.visibility = View.VISIBLE
        } else {
            progressBar1.visibility = View.GONE
        }
    }
}