package com.example.rewear.addClothes

import com.example.rewear.database.DataBaseHelper

class AddClothesPresenter (
    private val view: AddClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : AddClothesContract.Presenter {

}