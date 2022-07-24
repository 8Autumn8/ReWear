package com.example.rewear.editClothes

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData

class EditClothesPresenter(
    private val view: EditClothesFragment,
    private val db: DataBaseHelper = DataBaseHelper()
) : EditClothesContract.Presenter {
    override fun editClothes(clothesRecord: ClothesData) {
        db.updateClothes(clothesRecord)
    }
}