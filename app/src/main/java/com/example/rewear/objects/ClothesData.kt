package com.example.rewear.objects

import android.graphics.Bitmap
import java.io.Serializable
import java.sql.Blob

data class ClothesData(
    val clothes_id: Int?,
    val user_id: Int?,
    var clothes_pic: ByteArray?,
    var clothes_desc: String?,
    val date_created: String?,
    val category_id: Int?,
    val category_name: String?,
    val total_days_worn: Int?,
    val last_worn: String?
) : Serializable{
    constructor(user_id: Int?,
                clothes_pic: ByteArray?,
                clothes_desc: String?) :
            this(0,
                user_id,
                clothes_pic,
                clothes_desc,
                "9999-9-9",
                0,
                "null",
                0,
                "9999-99-9");
    constructor(user_id: Int?) :
        this(0,
        user_id,
        null,
        "",
        "9999-9-9",
        0,
        "null",
        0,
        "9999-99-9");
    constructor() : this(null, null,null,null,null,null,null,null,null)




}