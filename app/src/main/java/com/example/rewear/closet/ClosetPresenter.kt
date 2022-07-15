package com.example.rewear.closet

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class ClosetPresenter (
    private val view: ClosetContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClosetContract.Presenter {
    override fun getCategories(user_id: Int) : List<ClothesCategoryData>? {
        return  db.getClothesCategoryByUserID(user_id)
    }
    override fun getPictures(clothesCategory: Int): List<ClothesData>?{
        return db.getClothesByID(clothesCategory)
    }
}