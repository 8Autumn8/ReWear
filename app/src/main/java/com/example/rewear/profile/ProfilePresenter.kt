package com.example.rewear.profile

import com.example.rewear.database.DataBaseHelper

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
)
    : ProfileContract.Presenter {

}