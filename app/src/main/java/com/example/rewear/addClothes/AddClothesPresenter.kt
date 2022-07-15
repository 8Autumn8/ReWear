package com.example.rewear.addClothes

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData

class AddClothesPresenter (
    private val view: AddClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) : AddClothesContract.Presenter {
    override fun addClothes( user_id: Int?,
                             img: ByteArray,
                             clothes_desc: String?,
                             date_created: String){
        val clothes = ClothesData(user_id,img,clothes_desc,date_created)
        db.addClothes(clothes)
    }
}