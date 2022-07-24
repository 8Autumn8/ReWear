package com.example.rewear.closetAdaptor

import com.example.rewear.objects.ClothesData

class ClosetAdaptorContract {
    interface View {
        fun returnGetPicturesByCategory(pictureCategories: List<ClothesData>?)
    }

    interface Presenter {
        fun getPicturesByCategory(pictureCategory: Int?)
    }
}