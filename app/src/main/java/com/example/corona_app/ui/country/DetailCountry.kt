package com.example.corona_app.ui.country

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.corona_app.R

class DetailCountry: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_total)
        val confirmed :TextView = findViewById(R.id.confirmed_number)
        val recover:TextView = findViewById(R.id.recover_number)
        val death:TextView = findViewById(R.id.death_number)
        val active:TextView = findViewById(R.id.active_number)

        val negara = intent.getParcelableExtra(EXTRA_DATA) as CountryData

        supportActionBar?.title = negara.country
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        confirmed.text = negara.confirmed
        recover.text = negara.recovered
        death.text = negara.deaths
        active.text = negara.active
    }
    companion object {
        const val EXTRA_DATA = ""
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}