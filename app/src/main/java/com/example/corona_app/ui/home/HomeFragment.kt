package com.example.corona_app.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corona_app.BuildConfig
import com.example.corona_app.R
import com.example.corona_app.ui.country.CountryData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_world.*
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_world, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        org.text = "Orang"
        org1.text = "Orang"
        org2.text = "Orang"
        setDataDeath()
        setDataIndonesia()
        setDataPositif()
        setDataSembuh()
    }
    internal fun setDataPositif(){
        val client = AsyncHttpClient()
        val url = BuildConfig.URL_API + "/positif"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try{
                    val result = String(responseBody!!)
                    val data1 = JSONObject(result)
                    positif.text = data1.getString("name")
                    number_positif.text = data1.getString("value")
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
    internal fun setDataSembuh(){
        val client = AsyncHttpClient()
        val url = BuildConfig.URL_API + "/sembuh"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try{
                    val result = String(responseBody!!)
                    val data1 = JSONObject(result)
                    sembuh.text = data1.getString("name")
                    number_sembuh.text = data1.getString("value")
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
    internal fun setDataDeath(){
        val client = AsyncHttpClient()
        val url = BuildConfig.URL_API +"/meninggal"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try{
                    val result = String(responseBody!!)
                    val data1 = JSONObject(result)
                    meninggal.text = data1.getString("name")
                    number_meninggal.text = data1.getString("value")
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
    internal fun setDataIndonesia(){
        val client = AsyncHttpClient()
        val url = BuildConfig.URL_API + "/indonesia"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try{
                    val result = String(responseBody!!)
                    val data1 = JSONArray(result)
                    val item = data1.getJSONObject(0)
                    indon.text = item.getString("name")
                    val txt = "${item.getString("positif")} Positif, ${item.getString("sembuh")} Sembuh, ${item.getString("meninggal")} Meninggal"
                    total.text = txt
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
}