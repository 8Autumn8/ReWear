package com.example.rewear.addEditClothes

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class AddClothesPresenter (
    private val view: AddClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : AddClothesContract.Presenter {

    override fun addClothes(clothes: ClothesData){
        val clothesID: Int? = db.addClothes(clothes)
        view.setClothesID(clothesID!!)
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, user_id: Int, clothes_id: Int){
        if (category!!.size != 0) {
            var belongTo: MutableList<ClothesBelongsToData> = mutableListOf()
            db.addClothesCategory(category!!)
            for (cat: ClothesCategoryData in category){
                val clothingList = db.getClothesCategory(user_id, cat)

                belongTo.add(ClothesBelongsToData(clothingList!!.category_id, clothes_id))
            }
            db.addClothesBelongsTo(belongTo)
        }
    }

    override fun addToTags(category: List<ClothesBelongsToData>?, clothesID: Int){
        if (category!!.size != 0) {
            for (cat: ClothesBelongsToData in category){
                cat.clothes_id = clothesID
            }
            db.addClothesBelongsTo(category!!)
        }
    }

}