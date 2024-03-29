package com.example.rewear.database

import com.example.rewear.objects.LikedData


interface LikedInterface {
    fun getLiked(picture_id: Int) : List<LikedData>?

    fun addLiked(info: LikedData)

    fun deleteLiked(info: LikedData)

    fun getUserLiked(user_id: Int, group_id: Int) : List<LikedData>?

}