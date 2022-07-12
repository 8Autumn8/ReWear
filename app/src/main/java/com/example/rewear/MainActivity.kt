package com.example.rewear

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.groups.GroupsFragment
import com.example.rewear.profile.*
import androidx.fragment.app.Fragment
import com.example.rewear.viewUser.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    val TAG = "MAINACTIVITY"


    //setsCurrentFragment(groupsFragment)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groupsFragment = GroupsFragment()
        val profileFragment = ProfileFragment()
        setsCurrentFragment(groupsFragment)

        bottom_navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_group -> {
                    setsCurrentFragment(groupsFragment)
                    Log.d(TAG, "GROUPS SELECTED")
                }
                R.id.nav_profile -> {
                    setsCurrentFragment(profileFragment)
                    Log.d(TAG, "Profile SELECTED")
                }



            }

            return@setOnItemSelectedListener true
        }
    }


    private fun setsCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply{
        replace(R.id.fl_wrapper,fragment)
        commit()
    }


}




