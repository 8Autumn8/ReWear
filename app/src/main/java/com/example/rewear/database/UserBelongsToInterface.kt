package com.example.rewear.database

import com.example.rewear.objects.UserBelongsToData

interface UserBelongsToInterface {
    fun getUserBelongsTo(user_id: Int): List<UserBelongsToData>?

    fun addUserBelongsTo(belongsTo: UserBelongsToData)

    fun deleteUserBelongsTo(belongsTo: UserBelongsToData)
}