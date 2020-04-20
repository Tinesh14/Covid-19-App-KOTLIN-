package com.example.corona_app.ui.virus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class CasesViewModel : ViewModel() {

    private val listCountryData = MutableLiveData<ArrayList<CasesData>>()
    internal fun setCase(){
        val client = AsyncHttpClient()
        val listItems = ArrayList<CasesData>()
        val url = "https://indonesia-covid-19.mathdro.id/api/kasus"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONObject("data")
                    val data1 = list.getJSONArray("nodes")
                    for (i in 0 until 30){
                        val real = data1.getJSONObject(i)
                        val item = CasesData()
                        item.umur = real.getInt("umur")
                        item.negara = real.getString("wn")
                        item.gender = real.getString("gender")
                        item.province = real.getString("klaster")
                        item.status = real.getString("status")
                        listItems.add(item)
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
    internal fun getCase():MutableLiveData<ArrayList<CasesData>>{
        return listCountryData
    }
}
