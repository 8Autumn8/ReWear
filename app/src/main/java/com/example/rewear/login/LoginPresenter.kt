package com.example.rewear.login

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData

class LoginPresenter(
    private val view: LoginContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
)
    : LoginContract.Presenter {
    //MVP ^^^

    override fun verifyUser(userField:String, pwdField:String){
        val compareUser: UserData? = db.getUser(userField)
        var uid = -1
        if ((compareUser != null) && (compareUser.username == userField) && (compareUser.password == pwdField)){
            uid = compareUser.user_id!!
        }
        view.returnVerifiedUserID(uid)
    }
}