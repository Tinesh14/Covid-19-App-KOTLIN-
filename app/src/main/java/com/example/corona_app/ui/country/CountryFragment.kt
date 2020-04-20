package com.example.corona_app.ui.country

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
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {

    private lateinit var rvCountry: RecyclerView
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCountry= view.findViewById(R.id.rv_negara)
        rvCountry.setHasFixedSize(true)
        adapter = CountryAdapter()
        adapter.notifyDataSetChanged()
        rvCountry.layoutManager = LinearLayoutManager(activity)
        rvCountry.adapter = adapter

        countryViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(CountryViewModel::class.java)
        countryViewModel.setCountry()
        countryViewModel.getCountry().observe(viewLifecycleOwner, Observer { items ->
            if (items != null){
                adapter.setData(items)
                showLoading(false)
            }
        })
        showLoading(true)
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}