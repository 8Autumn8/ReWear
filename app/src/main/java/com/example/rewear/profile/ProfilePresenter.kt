package com.example.rewear.profile

import com.example.rewear.database.DataBaseHelper

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : ProfileContract.Presenter {

    override fun getCurrentUser(userID: Int) {
        // user should already exist at this point, so it should return a non-null value
        view.returnCurrentUser(db.getUserByID(userID)!!)
    }

    override fun deleteCurrentUser(userID: Int) {
        db.deleteUser(userID)
    }
}