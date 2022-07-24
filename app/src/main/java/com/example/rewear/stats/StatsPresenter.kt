package com.example.rewear.stats

import com.example.rewear.database.DataBaseHelper
import java.sql.Date

class StatsPresenter(
    private val view: StatsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : StatsContract.Presenter {

    private var WEEK = 604800000L
    private var MONTH = 2629800000L

    /**
     * This function grabs the most worn piece of clothing from the given user's closet through
     * executing a SQL statement from the database.
     * @param userID the user's ID used to find the most worn clothing from
     * @return A `ClothesData` of the user's most worn clothing, or `null` if it couldn't be
     * fetched.
     */
    override fun getMostWorn(userID: Int) {
        view.returnMostWorn(db.getMostWorn(userID))
    }

    override fun getPercentageWornFromDate(userID: Int) {
        val lastWeekDate = Date(java.util.Date().time - WEEK)
        val lastMonthDate = Date(java.util.Date().time - MONTH)

        val percentageLastWeek = getPercentageWorn(userID, lastWeekDate)
        val percentageLastMonth = getPercentageWorn(userID, lastMonthDate)

        view.returnPercentageWornFromDate(percentageLastWeek, percentageLastMonth)
    }

    private fun getPercentageWorn(userID: Int, date: Date): Int {
        var percentage = 0
        val numClothesInCloset = db.countClothes(userID)
        val wornSinceDate = db.getNumWornSinceDate(userID, date)

        if (numClothesInCloset != 0 && wornSinceDate != 0)
                percentage = (wornSinceDate!!.toFloat() / numClothesInCloset!!.toFloat() * 100).toInt()

        return percentage
    }
}