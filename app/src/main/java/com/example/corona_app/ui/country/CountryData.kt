package com.example.corona_app.ui.country

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryData (
    var country:String = "",
    var confirmed:String = "",
    var deaths:String = "",
    var recovered:String = "",
    var active:String = ""
):Parcelable

