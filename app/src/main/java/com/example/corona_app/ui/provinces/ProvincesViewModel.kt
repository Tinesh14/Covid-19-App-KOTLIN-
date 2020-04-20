package com.example.corona_app.ui.provinces

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corona_app.BuildConfig
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ProvincesViewModel : ViewModel() {
    companion object {
        private const val API_URL =
            BuildConfig.URL_API
    }
    private val listProvinceData = MutableLiveData<ArrayList<ProvincesData>>()
    internal fun setProvinces(){
        val client = AsyncHttpClient()
        val listItems = ArrayList<ProvincesData>()
        val url = "$API_URL/indonesia/provinsi/"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val data1 = JSONArray(result)
                    for (i in 0 until data1.length()){
                        val province = data1.getJSONObject(i).getJSONObject("attributes")
                        val data = ProvincesData()
                        data.province = province.getString("Provinsi")
                        data.confirmed = province.getInt("Kasus_Posi")
                        data.recovered = province.getInt("Kasus_Semb")
                        data.death = province.getInt("Kasus_Meni")
                        listItems.add(data)
                    }
                    listProvinceData.postValue(listItems)
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
    internal fun getProvinces():MutableLiveData<ArrayList<ProvincesData>>{
        return listProvinceData
    }
}