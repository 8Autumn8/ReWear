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
        clothes.clothes_id = clothesID
        val date = DateWornData(clothes.clothes_id,LocalDate.now().toString())
        if (clothes.last_worn != null){

            db.addDateWorn(date)
        } else {
            db.deleteDateWorn(date)
        }
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, user_id: Int, clothes_id: Int){
        if (category!!.size != 0) {
            db.addClothesCategory(category!!, clothes_id)
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