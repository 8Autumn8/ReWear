package com.example.rewear.login

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData

class LoginPresenter(
    private val view: LoginContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
)
    : LoginContract.Presenter {
    //MVP ^^^

    override fun verifyUser(userField:String, pwdField:String) : Boolean{
        val compareUser: UserData? = db.getUser(userField)

        return (compareUser != null) && (compareUser.username == userField) && (compareUser.password == pwdField)

     }
}