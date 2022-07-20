package com.example.rewear.stats

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData
import java.sql.Date

class StatsPresenter(
    private val view: StatsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : StatsContract.Presenter {

    /**
     * This function grabs the most worn piece of clothing from the given user's closet through
     * executing a SQL statement from the database.
     * @param userID the user's ID used to find the most worn clothing from
     * @return A `ClothesData` of the user's most worn clothing, or `null` if it couldn't be
     * fetched.
     */
    override fun getMostWorn(userID: Int): ClothesData? {
        return db.getMostWorn(userID)
    }

    override fun getPercentageWornFromDate(userID: Int, date: Date): Int {
        val numClothesInCloset = db.countClothes(userID)
        val wornSinceGivenDate = db.getNumWornSinceDate(userID,date)


        if (numClothesInCloset == 0 || wornSinceGivenDate == 0)
            return 0
        return (wornSinceGivenDate!!.toFloat() / numClothesInCloset!!.toFloat() * 100).toInt()
    }
}