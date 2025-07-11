package com.doston.tibbiykomak.data

import java.io.Serializable

data class ReminderData(
    val id: Int =0,
    val name: String,
    val desc: String,
    val date: List<String>,
    val times: List<String>
) : Serializable
