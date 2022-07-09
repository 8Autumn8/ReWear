package com.example.rewear.groups

import com.example.rewear.DataBaseHelper

class GroupsPresenter (
    private val view: GroupsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :GroupsContract.Presenter {
            
}