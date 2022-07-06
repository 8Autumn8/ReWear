package com.example.rewear.login

import android.content.Context
import android.view.MenuItem
import android.util.Log

interface LoginContract {
    interface View{

    }
    interface Presenter{
        fun verifyUser(userField:String, pwdField:String)
    }
}