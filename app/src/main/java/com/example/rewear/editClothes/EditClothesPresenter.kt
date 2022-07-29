package com.example.rewear.editClothes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.DateWornData
import java.time.LocalDate

class EditClothesPresenter(
    private val view: EditClothesFragment,
    private val db: DataBaseHelper = DataBaseHelper()
) : EditClothesContract.Presenter {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun editClothes(clothesRecord: ClothesData) {
        db.updateClothes(clothesRecord)
        val date = DateWornData(clothesRecord.clothes_id, LocalDate.now().toString())
        if (clothesRecord.last_worn != null){
            db.addDateWorn(date)
        } else {
            db.deleteDateWorn(date)
        }
    }

    override fun addNewTags(category: List<ClothesCategoryData>?, clothes: ClothesData){
        if (category!!.size != 0) {
            var belongTo: MutableList<ClothesBelongsToData> = mutableListOf()
            db.addClothesCategory(category!!, clothes.clothes_id!!)
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


    override fun deleteClothes(clothes: ClothesData){
        db.deleteClothes(clothes.clothes_id!!)
    }

}