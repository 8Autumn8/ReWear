package com.example.rewear.database

import com.example.rewear.objects.UserData


interface UserInterface {
    fun getUser(usernameInput: String) : UserData?

    fun addUser(user: UserData)

    fun deleteUser()

    fun updateUser(updatedUser: UserData)
}