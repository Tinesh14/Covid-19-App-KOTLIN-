package com.example.corona_app.ui.provinces

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.corona_app.R
import com.example.corona_app.ui.country.DetailCountry

class DetailProvince:AppCompatActivity() {
 companion object{
     const val EXTRA_DATA=""
 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_total)
        val confirmed : TextView = findViewById(R.id.confirmed_number)
        val recover: TextView = findViewById(R.id.recover_number)
        val death: TextView = findViewById(R.id.death_number)
        val active: TextView = findViewById(R.id.active_number)

        val province = intent.getParcelableExtra(DetailCountry.EXTRA_DATA) as ProvincesData

        supportActionBar?.title = province.province
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        confirmed.text = province.confirmed.toString()
        recover.text = province.recovered.toString()
        death.text = province.death.toString()
        active.text = (province.confirmed - (province.recovered + province.death)).toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}