package com.example.rewear.stats

import com.example.rewear.database.DataBaseHelper

class StatsPresenter (
    private val view: StatsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :StatsContract.Presenter {
            
}