package com.example.rewear.editClothes

import com.example.rewear.objects.ClothesData

class EditClothesContract {
    interface View {

    }

    interface Presenter {
        fun editClothes(clothesRecord: ClothesData)
    }

}