package com.example.rewear.addEditClothes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.DateWornData
import java.time.LocalDate

class AddClothesPresenter (
    private val view: AddClothesContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : AddClothesContract.Presenter {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addClothes(clothes: ClothesData){
        val clothesID: Int? = db.addClothes(clothes)
        view.setClothesID(clothesID!!)
        val date = DateWornData(clothes.clothes_id,LocalDate.now().toString())
        if (clothes.last_worn != null){
            db.addDateWorn(date)
        } else {
            db.deleteDateWorn(date)
        }
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, user_id: Int, clothes_id: Int){
        if (category!!.size != 0) {
            var belongTo: MutableList<ClothesBelongsToData> = mutableListOf()
            db.addClothesCategory(category!!)
            for (cat: ClothesCategoryData in category){
                val clothingList = db.getClothesCategory(user_id, cat)

                belongTo.add(ClothesBelongsToData(clothes_id,clothingList!!.category_id))
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