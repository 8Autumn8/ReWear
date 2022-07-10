package com.example.rewear.database

import com.example.rewear.objects.UserData


interface UserInterface {
    fun getUser(userNameInput: String) : UserData?

    fun addUser(user: UserData)

    fun deleteUser(user:UserData)

    fun updateUser(user:UserData)
}