package com.example.rewear.createUser

interface CreateUserContract {
    interface View {

    }
    interface Presenter {
        fun addUser(fName: String?, lName:String?, username: String?, password:String?)
        fun checkUserExist(userName: String) : Boolean
    }
}