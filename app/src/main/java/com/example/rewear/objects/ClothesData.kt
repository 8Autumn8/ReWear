package com.example.rewear.objects

import android.graphics.Picture
import java.util.*

data class ClothesData(
    val clothes_id: Int?,
    val user_id: Int?,
    val clothes_pic: ByteArray?,
    val clothes_desc: String?,
    val date_created: String
)