package com.example.rewear.closet

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesCategoryData

class ClosetPresenter (
    private val view: ClosetContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClosetContract.Presenter {
    override fun getCategories(user_id: Int) : List<ClothesCategoryData>? {
        return  db.getClothesCategoryByUserID(user_id)
    }
}