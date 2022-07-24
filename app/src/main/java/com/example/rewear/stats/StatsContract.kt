package com.example.rewear.stats

import com.example.rewear.objects.ClothesData

// this has informal minh notes i need this for my sanity
interface StatsContract {
    // talks to the xml files; the frontend
    interface View {
        fun returnMostWorn(mostWorn: ClothesData?)
        fun returnPercentageWornFromDate(percentageLastWeek: Int, percentageLastMonth: Int)
    }
    // talks to the database
    interface Presenter {
        fun getMostWorn(userID: Int)
        fun getPercentageWornFromDate(userID: Int)
    }
}