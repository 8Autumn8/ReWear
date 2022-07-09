package com.example.rewear.createUser

import com.example.rewear.DataBaseHelper

class CreateUserPresenter(
    private val view: CreateUserContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : CreateUserContract.Presenter {

}