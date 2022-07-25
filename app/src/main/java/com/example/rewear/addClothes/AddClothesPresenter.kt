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
        db.addClothes(clothes)
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, clothes: ClothesData){
        if (category!!.size != 0) {
            var belongTo: MutableList<ClothesBelongsToData> = mutableListOf()
            db.addClothesCategory(category!!)
            for (cat: ClothesCategoryData in category){
                val clothingList =
                    clothes!!.category_id?.let { clothes!!.user_id?.let { it1 ->
                        db.getClothesCategory(
                            it1, it)
                    } }
                val clothesBelongs = ClothesBelongsToData(clothingList!!.category_id,clothes.user_id)

                belongTo.add(clothesBelongs)
            }
            db.addClothesBelongsTo(belongTo)
        }
    }

    override fun addToTags(category: List<ClothesBelongsToData>?){
        if (category!!.size != 0) {
            db.addClothesBelongsTo(category!!)
        }
    }

}