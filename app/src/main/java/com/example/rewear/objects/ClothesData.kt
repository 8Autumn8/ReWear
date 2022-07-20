package com.example.rewear.objects

data class ClothesData(
    var clothes_id: Int?,
    var user_id: Int?,
    var clothes_pic: ByteArray?,
    var clothes_desc: String?,
    var date_created: String?
){
    constructor(user_id: Int?,
                clothes_pic: ByteArray?,
                clothes_desc: String?,
                date_created: String?) : this(null, user_id, clothes_pic, clothes_desc,date_created);
    constructor() : this(null,null,null,null,null)
}