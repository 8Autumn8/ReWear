package com.example.rewear.database

import com.example.rewear.objects.PictureData

class PictureDB: PictureInterface, GenerateConnection() {
    override fun addPicture(pictureData: PictureData){
        TODO("Not yet implemented")
    }

    override fun deletePicture(picture_id: Int) {
        TODO("Not yet implemented")
    }

    override fun getPicture(picture_id: Int) : PictureData? {
        TODO("Not yet implemented")
    }

    override fun updatePicture(pictureData: PictureData) {
        TODO("Not yet implemented")
    }
}