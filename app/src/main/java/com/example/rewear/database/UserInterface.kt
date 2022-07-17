package com.example.rewear.database

import com.example.rewear.objects.UserData


interface UserInterface {
    fun getUser(usernameInput: String) : UserData?

    fun getUserByID(userID: Int): UserData?

    fun addUser(user: UserData)

    fun deleteUser(user_id: Int)

    fun updateUser(updatedUser: UserData)
}