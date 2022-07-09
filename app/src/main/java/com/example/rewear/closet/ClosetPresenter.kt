package com.example.rewear.closet

import com.example.rewear.database.DataBaseHelper

class ClosetPresenter (
    private val view: ClosetContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClosetContract.Presenter {

}