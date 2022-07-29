package com.example.rewear.editProfile

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData

class EditProfilePresenter(
    private val view: EditProfileContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : EditProfileContract.Presenter {

    override fun getCurrentUser(userID: Int): UserData {
        // it should be assumed that the user already exists in the database
        return db.getUserByID(userID)!!
    }

    override fun updateUser(updatedUser: UserData) {
        db.updateUser(updatedUser)
    }
}