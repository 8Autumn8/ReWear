package com.example.rewear.closet

import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

interface ClosetContract {
    interface View {

    }
    interface Presenter {
        fun getCategories(user_id: Int) : List<ClothesCategoryData>?
        fun getPictures(pictureCategory: Int): List<ClothesData>?

    }
}