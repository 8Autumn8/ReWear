package com.example.rewear.editClothes

import com.example.rewear.database.ClothesBelongsToDB
import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData

class EditClothesPresenter(
    private val view: EditClothesFragment,
    private val db: DataBaseHelper = DataBaseHelper()
) : EditClothesContract.Presenter {
    override fun editClothes(clothesRecord: ClothesData) {
        db.updateClothes(clothesRecord)
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, clothes: ClothesData){
        if (category!!.size != 0) {
            var belongTo: MutableList<ClothesBelongsToData> = mutableListOf()
            db.addClothesCategory(category!!)
            for (cat: ClothesCategoryData in category){
                val clothingList =
                    clothes!!.category_id?.let { clothes!!.user_id?.let { it1 ->
                        db.getClothesCategory(
                            it1, cat)
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

    override fun deleteFromTags(category: List<ClothesBelongsToData>?){
        if (category!!.size != 0){
            db.deleteClothesBelongsTo(category!!)
        }

    }
}