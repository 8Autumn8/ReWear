package com.example.rewear.addEditClothes

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData

class AddEditClothesPresenter (
    private val view: AddEditClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : AddEditClothesContract.Presenter {

}