package com.example.rewear.clothtracking

import com.example.rewear.DataBaseHelper
import com.example.rewear.login.LoginContract

class ClothesTrackingPresenter (
    private val view: ClothesTrackingContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClothesTrackingContract.Presenter {

}