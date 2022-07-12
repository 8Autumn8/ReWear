package com.example.rewear.leaderboard

import com.example.rewear.database.DataBaseHelper

class LeaderboardPresenter (
    private val view: LeaderboardContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :LeaderboardContract.Presenter {
            
}