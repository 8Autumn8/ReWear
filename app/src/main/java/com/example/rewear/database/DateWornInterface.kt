package com.example.rewear.database

import com.example.rewear.objects.DateWorn

interface DateWornInterface {

    fun getDateWorn(clothes_id: Int) : List<DateWorn>?

    fun addDateWorn(dateWorn: DateWorn)

    fun deleteDateWorn(dateWorn: DateWorn)

}