package com.example.corona_app.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class NewsViewModel : ViewModel() {
    private val listNewsData = MutableLiveData<ArrayList<NewsData>>()
    internal fun setNews(){
        val client = AsyncHttpClient()
        val listItems = ArrayList<NewsData>()
        val url = "http://newsapi.org/v2/top-headlines?country=id&category=health&apiKey=3bc0d5b6892f4281ac83740321249f09"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val data1 = responseObject.getJSONArray("articles")
                    for(i in 0 until data1.length()){
                        val real = data1.getJSONObject(i)
                        val item = NewsData()
                        item.urlImage = real.getString("urlToImage")
                        item.url = real.getString("url")
                        item.author = real.getString("author")
                        item.description = real.getString("description")
                        item.publishedAt = real.getString("publishedAt")
                        item.title = real.getString("title")
                        val last = real.getJSONObject("source")
                        item.source = last.getString("name")
                        listItems.add(item)
                    }
                    listNewsData.postValue(listItems)
                }catch (e:Exception){
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }
    internal fun getNews():MutableLiveData<ArrayList<NewsData>>{
        return listNewsData
    }
}
