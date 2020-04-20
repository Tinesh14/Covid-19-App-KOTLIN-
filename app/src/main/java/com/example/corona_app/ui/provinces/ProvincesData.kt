package com.example.corona_app.ui.provinces

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProvincesData(
    var province:String = "",
    var confirmed:Int = 0,
    var recovered:Int= 0,
    var death:Int = 0
):Parcelable