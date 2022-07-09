package com.example.rewear.groups

import com.example.rewear.database.DataBaseHelper

class GroupsPresenter (
    private val view: GroupsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :GroupsContract.Presenter {
            
}