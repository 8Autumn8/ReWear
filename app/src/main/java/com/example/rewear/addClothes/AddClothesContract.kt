package com.example.rewear.addClothes

class AddClothesContract {
    interface View {

    }

    interface Presenter {
        fun addClothes( user_id: Int?,
                        img: ByteArray,
                        clothes_desc: String?,
                        date_created: String){

        }
    }
}