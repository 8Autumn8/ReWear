package com.example.rewear.database

import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.DateWornData
import java.util.*

interface DateWornInterface {

    fun getDateWorn(clothes_id: Int) : List<DateWornData>?

    fun addDateWorn(dateWorn: DateWornData)

    fun deleteDateWorn(dateWorn: DateWornData)

    fun getMostWorn(user_id: Int) : ClothesData?

    fun getNumWornSinceDate(user_id: Int, date: Date): Int?

}