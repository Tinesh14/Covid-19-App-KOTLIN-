package com.example.corona_app.ui.virus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_app.R
import kotlinx.android.synthetic.main.fragment_cases.*


class CasesFragment : Fragment() {

    private lateinit var viewModel: CasesViewModel
    private lateinit var rvCases: RecyclerView
    private lateinit var adapter: CasesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_cases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCases = view.findViewById(R.id.rv_cases)
        rvCases.setHasFixedSize(true)
        adapter = CasesAdapter()
        adapter.notifyDataSetChanged()
        rvCases.layoutManager = LinearLayoutManager(activity)
        rvCases.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(CasesViewModel::class.java)
        viewModel.setCase()
        viewModel.getCase().observe(viewLifecycleOwner, Observer {items ->
            if(items != null){
                adapter.setData(items)
                showLoading(false)
            }
        })
        showLoading(true)
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar2.visibility = View.VISIBLE
        } else {
            progressBar2.visibility = View.GONE
        }
    }
}
