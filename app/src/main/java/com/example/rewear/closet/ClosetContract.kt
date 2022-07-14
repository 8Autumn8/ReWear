package com.example.rewear.closet

import com.example.rewear.objects.ClothesCategoryData

interface ClosetContract {
    interface View {

    }
    interface Presenter {
        fun getCategories(user_id: Int) : List<ClothesCategoryData>?
    }
}