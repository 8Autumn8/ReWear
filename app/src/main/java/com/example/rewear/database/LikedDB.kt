package com.example.rewear.database

import com.example.rewear.objects.LikedData

class LikedDB:LikedInterface, GenerateConnection() {
    override fun addLiked(info: LikedData) {
        TODO("Not yet implemented")
    }

    override fun deleteLiked(info: LikedData) {
        TODO("Not yet implemented")
    }

    override fun getLiked(picture_id: Int) : List<LikedData>? {
        TODO("Not yet implemented")
        return null
    }

}