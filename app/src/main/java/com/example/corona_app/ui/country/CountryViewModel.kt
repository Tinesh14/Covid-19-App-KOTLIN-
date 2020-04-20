package com.example.corona_app.ui.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corona_app.BuildConfig
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class CountryViewModel : ViewModel() {

    companion object {
        private const val API_URL =
            BuildConfig.URL_API
    }
    private val listCountryData = MutableLiveData<ArrayList<CountryData>>()
    internal fun setCountry(){
        val client = AsyncHttpClient()
        val listItems = ArrayList<CountryData>()
        client.get(API_URL, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val data1 = JSONArray(result)
                    for(i in 0 until data1.length()){
                        val country = data1.getJSONObject(i).getJSONObject("attributes")
                        val data = CountryData()
                        data.active = country.getInt("Active").toString()
                        data.confirmed = country.getInt("Confirmed").toString()
                        data.country = country.getString("Country_Region")
                        data.deaths = country.getInt("Deaths").toString()
                        data.recovered = country.getInt("Recovered").toString()
                        listItems.add(data)
                    }
                    listCountryData.postValue(listItems)
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
    internal fun getCountry():MutableLiveData<ArrayList<CountryData>>{
        return listCountryData
    }
}