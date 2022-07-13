package com.example.rewear.clothestracking

import com.example.rewear.database.DataBaseHelper

class ClothesTrackingPresenter (
    private val view: ClothesTrackingContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClothesTrackingContract.Presenter {

}