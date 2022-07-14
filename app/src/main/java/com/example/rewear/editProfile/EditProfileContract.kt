package com.example.rewear.editProfile

import com.example.rewear.objects.UserData

interface EditProfileContract {

    interface View {
    }

    interface Presenter {
        fun getCurrentUser(userID: Int): UserData
        fun updateUser(updatedUser: UserData)
    }
}