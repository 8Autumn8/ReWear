package com.example.rewear.database

import com.example.rewear.objects.PictureData

interface PictureInterface {

    fun getPicture(picture_id: Int) : PictureData?

    fun addPicture(pictureData: PictureData)
    fun deletePicture(picture_id: Int)
    fun getPictureByGroup(group_id:Int) : List<PictureData>?
     fun updatePicture(pictureData: PictureData)
}