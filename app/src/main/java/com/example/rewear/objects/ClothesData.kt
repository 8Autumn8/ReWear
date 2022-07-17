package com.example.rewear.objects

import android.graphics.Bitmap
import java.sql.Blob

data class ClothesData(
    val clothes_id: Int?,
    val user_id: Int?,
    val clothes_pic: ByteArray?,
    val clothes_desc: String?,
    val date_created: String?
){
    constructor(user_id: Int?,
                clothes_pic: ByteArray?,
                clothes_desc: String?,
                date_created: String?) : this(null, user_id, clothes_pic, clothes_desc,date_created)


}