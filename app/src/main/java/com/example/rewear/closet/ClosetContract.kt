package com.example.rewear.closet

import com.example.rewear.objects.ClothesCategoryData

interface ClosetContract {
    interface View {
        fun returnGetCategories(clothesCategories: List<ClothesCategoryData>?)
    }
    interface Presenter {
        fun getCategories(user_id: Int)
    }
}