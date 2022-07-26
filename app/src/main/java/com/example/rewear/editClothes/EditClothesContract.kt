package com.example.rewear.editClothes

import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class EditClothesContract {
    interface View {

    }

    interface Presenter {
        fun editClothes(clothesRecord: ClothesData)

        fun addNewTags(category: List<ClothesCategoryData>?, clothes: ClothesData)

        fun addToTags(category: List<ClothesBelongsToData>?)

        fun deleteFromTags(category: List<ClothesBelongsToData>?)

        fun deleteClothes(clothes: ClothesData)
    }

}