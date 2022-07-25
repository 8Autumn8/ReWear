package com.example.rewear.addEditClothes

import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class AddClothesContract {
    interface View {
    }

    interface Presenter {
        fun addClothes(clothes: ClothesData)

        fun addNewTags(category: List<ClothesCategoryData>?, clothes: ClothesData)

        fun addToTags(category: List<ClothesBelongsToData>?)
    }
}