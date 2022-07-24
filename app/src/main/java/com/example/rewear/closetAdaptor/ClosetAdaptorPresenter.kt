package com.example.rewear.closetAdaptor

import com.example.rewear.database.DataBaseHelper

class ClosetAdaptorPresenter(
    private val view: ClosetAdaptorContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : ClosetAdaptorContract.Presenter {

    override fun getPicturesByCategory(pictures: Int?) {
        view.returnGetPicturesByCategory(
            db.getClothesByClothesID(
                db.getClothesIDByCategory(
                    pictures!!
                )
            )
        )
    }
}