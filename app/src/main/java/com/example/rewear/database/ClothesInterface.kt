package com.example.rewear.database
import com.example.rewear.objects.ClothesData

interface ClothesInterface {

    fun getClothes(clothes_id: Int): ClothesData?

    fun addClothes(clothesObject: ClothesData)

    fun deleteClothes(clothes_id: Int)

    fun updateClothes(clothesObject: ClothesData)

    fun getClothesByID(clothesCategory: Int): List<ClothesData>?
}