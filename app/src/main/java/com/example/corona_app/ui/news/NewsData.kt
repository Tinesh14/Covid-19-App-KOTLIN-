package com.example.corona_app.ui.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData (
    var source:String = "",
    var author:String = "",
    var title:String = "",
    var description:String = "",
    var url:String = "",
    var urlImage:String = "",
    var publishedAt:String = ""
):Parcelable