package com.example.rewear.database

import com.example.rewear.objects.PictureGroupData

interface PictureGroupInterface {
    fun getPictureGroup(group_id: Int) : List<PictureGroupData>?

    fun addPictureGroup(picturegroup: PictureGroupData)

    fun deletePictureGroup(picturegroup: PictureGroupData)

}