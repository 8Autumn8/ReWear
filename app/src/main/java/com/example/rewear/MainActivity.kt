package com.example.rewear

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.groups.GroupsFragment
import com.example.rewear.profile.*
import androidx.fragment.app.Fragment
import com.example.rewear.viewUser.ProfileFragment
import res.menu.*

class MainActivity : AppCompatActivity(){

    val TAG = "MAINACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groupsFragment = GroupsFragment()
        val viewUser = ProfileFragment()


        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_group -> {
                    setsCurrentFragment(groupsFragment)
                    Log.i(TAG,"GROUPS SELECTED")
                }
            }
            when(it.itemId){
                R.id.nav_profile -> {
                    setsCurrentFragment(viewUser)
                    Log.i(TAG,"PROFLE SELECTED")
                }
            }

        }
    }


    private fun setsCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply{
        replace(R.id.fl_wrapper,fragment)
        commit()
    }


}