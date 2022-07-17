package com.example.rewear.profile

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
)
    : ProfileContract.Presenter {

    override fun getCurrentUser(userID: Int): UserData {
        // user should already exist at this point, so it should return a non-null value
        return db.getUserByID(userID)!!
    }

    override fun deleteCurrentUser(userID: Int) {
        db.deleteUser(userID)
    }
}