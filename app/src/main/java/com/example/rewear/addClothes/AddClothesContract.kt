package com.example.rewear.addEditClothes

import com.example.rewear.objects.ClothesData

class AddClothesContract {
    interface View {

    }

    interface Presenter {
        fun addClothes(clothes: ClothesData)

    }
}