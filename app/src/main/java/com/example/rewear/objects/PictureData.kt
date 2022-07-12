package com.example.rewear.objects

import android.graphics.Picture
import java.util.*

data class PictureData(val picture_id: Int?,
                       val user_id: Int?,
                       val picture: ByteArray?,
                       val caption: String?,
                       val date_posted: String?,
                       val username: String?
) {
    constructor(picture_id: Int?,
                user_id: Int?,
                picture: ByteArray,
                caption: String?,
                date_posted: String?,) : this(picture_id,user_id,picture,caption,date_posted, null)
}

