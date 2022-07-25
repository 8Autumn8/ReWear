package com.example.rewear.database

import com.example.rewear.objects.ClothesBelongsToData

interface ClothesBelongsToInterface {

    fun getClothesBelongsTo(user_id: Int) : List<ClothesBelongsToData>?

    fun addClothesBelongsTo(clothesBelongsToData: ClothesBelongsToData)

    fun addClothesBelongsTo(clothesBelongsToData: List<ClothesBelongsToData>)

    fun deleteClothesBelongsTo(clothesBelongsToData: ClothesBelongsToData)

    fun deleteClothesBelongsTo(clothesBelongsToData: List<ClothesBelongsToData>)

}