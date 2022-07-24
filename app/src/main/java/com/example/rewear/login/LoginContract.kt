package com.example.rewear.login

import android.content.Context

interface LoginContract {
    interface View{
         fun getContext(): Context
         fun returnVerifiedUserID(userID: Int)
    }
    interface Presenter{
        fun verifyUser(userField:String, pwdField:String)
    }
}