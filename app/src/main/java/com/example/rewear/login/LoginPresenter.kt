package com.example.rewear.login

import android.util.Log
import com.example.rewear.database.*
import com.example.rewear.objects.*

class LoginPresenter(
    private val view: LoginContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
)
    : LoginContract.Presenter {
    //MVP ^^^

    override fun verifyUser(userField:String, pwdField:String) : Boolean{
        val compareUser: UserData? = db.getUser(userField)
        var toReturn: Boolean = false
        println(compareUser!!.username)
        println(compareUser!!.password)
        println(userField)
        println(pwdField)
        if (compareUser != null && compareUser.username == userField && compareUser.password == pwdField){
            toReturn = true
        }
        Log.d("TEST", "TEST")
        println(toReturn)
        return toReturn

     }
}