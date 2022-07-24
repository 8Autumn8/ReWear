package com.example.rewear.createUser

interface CreateUserContract {
    interface View {
        fun returnCheckUserExist(userExists: Boolean)
    }
    interface Presenter {
        fun addUser(fName: String?, lName:String?, username: String?, password:String?)
        fun checkUserExist(userName: String)
    }
}