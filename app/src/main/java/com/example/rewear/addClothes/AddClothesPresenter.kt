package com.example.rewear.addEditClothes

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData

class AddClothesPresenter (
    private val view: AddClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : AddClothesContract.Presenter {

    override fun addClothes(clothes: ClothesData){
        db.addClothes(clothes)
    }
}