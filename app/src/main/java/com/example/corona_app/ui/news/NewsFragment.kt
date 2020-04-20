package com.example.corona_app.ui.news

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
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {
    private lateinit var adapter: NewsAdapter
    private lateinit var rvNews:RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNews = view.findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true)
        adapter = NewsAdapter()
        adapter.notifyDataSetChanged()
        rvNews.layoutManager = LinearLayoutManager(activity)
        rvNews.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(NewsViewModel::class.java)
        viewModel.setNews()
        viewModel.getNews().observe(viewLifecycleOwner, Observer{items ->
            if (items!=null){
                adapter.setData(items)
                showLoading(false)
            }
        })
        showLoading(true)
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar3.visibility = View.VISIBLE
        } else {
            progressBar3.visibility = View.GONE
        }
    }
}
