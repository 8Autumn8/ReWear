package com.example.rewear.database

import com.example.rewear.objects.ClothesCategoryData

interface ClothesCategoryInterface {

    fun getClothesCategory(category_id: Int) : ClothesCategoryData?

    fun getClothesCategory(user_id: Int, clothes: ClothesCategoryData) : ClothesCategoryData?

    fun getClothesCategoryByUserID(user_id: Int) : List<ClothesCategoryData>?

    fun  addClothesCategory(clothescategory: ClothesCategoryData)

    fun addClothesCategory(clothescategory: List<ClothesCategoryData>, clothesID: Int)


    fun deleteClothesCategory(category_id: Int)

    fun updateClothesCategory(clothescategory: ClothesCategoryData)
}