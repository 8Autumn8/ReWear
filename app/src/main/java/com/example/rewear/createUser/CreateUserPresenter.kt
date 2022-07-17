package com.example.rewear.createUser

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData

class CreateUserPresenter(
    private val view: CreateUserContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : CreateUserContract.Presenter {

    override fun addUser(fName: String?, lName:String?, username: String?, password:String?){
        val userData = UserData(null, fName, lName, username, password)
        db.addUser(userData)
    }

    override fun checkUserExist(userName: String) : Boolean{
        return db.getUser(userName) != null
    }
}