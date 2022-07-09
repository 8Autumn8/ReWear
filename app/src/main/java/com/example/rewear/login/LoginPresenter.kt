package com.example.rewear.login

import android.util.Log
import com.example.rewear.DataBaseHelper

class LoginPresenter(
    private val view: LoginContract.View,
    private val db: DataBaseHelper = DataBaseHelper())
    : LoginContract.Presenter {
    //MVP ^^^

    override fun verifyUser(userField:String, pwdField:String){
        db.getUser()
        Log.d("TEST", "TEST")
     }
}