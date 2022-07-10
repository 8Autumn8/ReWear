package com.example.rewear.database

import com.example.rewear.objects.BelongsToData

interface BelongsToInterface {
    fun getBelongsTo(username: String): BelongsToData?

    fun addBelongsTo(belongsTo: BelongsToData)

    fun deleteBelongsTo()
}