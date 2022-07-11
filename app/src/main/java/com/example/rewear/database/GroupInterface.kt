package com.example.rewear.database

import com.example.rewear.objects.GroupsData

interface GroupInterface {

    fun updateGroup(group: GroupsData)

    fun addGroup(group: GroupsData)

    fun deleteGroup(group_id: Int)

    fun getGroup(group_id: Int) : GroupsData?
}