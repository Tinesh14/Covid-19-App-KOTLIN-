package com.example.corona_app.ui.virus

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CasesData (
    var umur:Int = 0,
    var gender:String = "",
    var status:String = "",
    var province:String = "",
    var negara:String = ""
):Parcelable