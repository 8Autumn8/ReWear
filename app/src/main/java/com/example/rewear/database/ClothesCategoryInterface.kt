package com.example.rewear.database

import com.example.rewear.objects.ClothesCategoryData

interface ClothesCategoryInterface {

    fun getClothesCategory(category_id: Int) : ClothesCategoryData?

    fun getClothesCategoryByUserID(user_id: Int) : List<ClothesCategoryData>?

    fun  addClothesCategory(clothescategory: ClothesCategoryData)

    fun deleteClothesCategory(category_id: Int)

    fun updateClothesCategory(clothescategory: ClothesCategoryData)
}