package com.example.rewear.closet

import com.example.rewear.DataBaseHelper

class ClosetPresenter (
    private val view: ClosetContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : ClosetContract.Presenter {

}