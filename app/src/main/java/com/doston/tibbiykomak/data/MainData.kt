package com.doston.tibbiykomak.data

data class MainData(val category: String,
                    val problem: String,
                    val recommendedPills: List<Pill>,
                    val homeAdvice: List<String>)

