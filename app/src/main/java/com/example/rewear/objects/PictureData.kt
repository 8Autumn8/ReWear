package com.example.rewear.objects

import android.graphics.Picture
import java.util.*

data class PictureData(val picture_id: Int?,
                       val user_id: Int?,
                       val picture: ByteArray,
                       val caption: String?,
                       val date_posted: String?)
