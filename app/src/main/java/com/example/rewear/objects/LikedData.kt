package com.example.rewear.objects

data class LikedData(val user_id: Int?,
                     val picture_id: Int?, val count: Int?){
    constructor(user_id: Int?,
                picture_id: Int?) : this(user_id, picture_id, null)
}
