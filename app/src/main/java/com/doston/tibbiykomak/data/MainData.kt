package com.doston.tibbiykomak.data

import android.os.Parcelable
import java.io.Serializable

data class MainData(val category: String,
                    val problem: String,
                    val description:String,
                    val image:Int,
                    val recommendedPills: List<Pill>,
                    val homeAdvice: List<String>):Serializable

