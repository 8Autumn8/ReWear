package com.example.rewear.stats

import com.example.rewear.objects.ClothesData
import java.sql.Date

// this has informal minh notes i need this for my sanity
interface StatsContract {
    // talks to the xml files; the frontend
    interface View {

    }
    // talks to the database
    interface Presenter {
        fun getMostWorn(userID: Int): ClothesData?
        fun getPercentageWornFromDate(userID: Int, date: Date): Int
    }
}