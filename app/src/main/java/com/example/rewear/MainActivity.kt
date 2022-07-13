package com.example.rewear

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rewear.closet.ClosetFragment
import com.example.rewear.groups.GroupsFragment
import com.example.rewear.leaderboard.LeaderboardFragment
import com.example.rewear.stats.StatsFragment
import com.example.rewear.viewUser.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    val TAG = "MAINACTIVITY"


    //setsCurrentFragment(groupsFragment)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val id: String? = intent.getStringExtra("user_id")

        val bundle = Bundle()
        bundle.putString("user_id", id)

        val groupsFragment = GroupsFragment()
        val profileFragment = ProfileFragment()
        val closetFragment = ClosetFragment()
        val statsFragment = StatsFragment()
        val leaderboardFragment = LeaderboardFragment()

        groupsFragment.setArguments(bundle)
        profileFragment.setArguments(bundle)
        closetFragment.setArguments(bundle)
        statsFragment.setArguments(bundle)
        leaderboardFragment.setArguments(bundle)


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
                R.id.nav_closet -> {
                    setsCurrentFragment(closetFragment)
                    Log.d(TAG, "CLOSET SELECTED")
                }
                R.id.nav_stats -> {
                    setsCurrentFragment(statsFragment)
                    Log.d(TAG, "STATS SELECTED")
                }

                R.id.nav_leaderboard -> {
                    setsCurrentFragment(leaderboardFragment)
                    Log.d(TAG, "LeaderBoard SELECTED")
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



