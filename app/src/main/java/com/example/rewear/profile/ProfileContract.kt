package com.example.rewear.profile

import com.example.rewear.objects.UserData

interface ProfileContract {
    interface View {
        fun returnCurrentUser(databaseUser: UserData)
    }

    interface Presenter {
        fun getCurrentUser(userID: Int)
        fun deleteCurrentUser(userID: Int)
    }
}