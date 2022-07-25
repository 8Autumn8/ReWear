package com.example.rewear.addEditClothes

import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class AddClothesContract {
    interface View {
        fun setClothesID(id: Int)
    }

    interface Presenter {
        fun addClothes(clothes: ClothesData)

        fun addNewTags(category: List<ClothesCategoryData>?, user_id: Int, clothes_id: Int)

        fun addToTags(category: List<ClothesBelongsToData>?, clothesID: Int)
    }
}