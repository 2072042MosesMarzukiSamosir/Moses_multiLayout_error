package com.moses.api.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cordinate (
    @SerializedName("lon") val longtitude : Double,
    @SerializedName("lat") val latitude : Double
        ) : Parcelable