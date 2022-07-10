package com.example.rewear.database

import com.example.rewear.objects.UserBelongsToData

interface UserBelongsToInterface {
    fun getBelongsTo(username: String): UserBelongsToData?

    fun addBelongsTo(belongsTo: UserBelongsToData)

    fun deleteBelongsTo()
}