package com.example.rewear.profile

import com.example.rewear.objects.UserData

interface ProfileContract {
    interface View {


    }
    interface Presenter {
        fun getCurrentUser(userID: Int): UserData
        fun deleteCurrentUser(userID: Int)
    }
}