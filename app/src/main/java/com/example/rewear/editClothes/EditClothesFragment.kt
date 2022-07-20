package com.example.rewear.editClothes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rewear.MainActivity

import com.example.rewear.R
import com.example.rewear.addEditClothes.AddEditClothesActivity
import com.example.rewear.addEditClothes.AddEditClothesContract
import kotlinx.android.synthetic.main.fragment_addclothes.*
import kotlinx.android.synthetic.main.fragment_editclothes.*

class EditClothesFragment : Fragment(), EditClothesContract.View{
    var user_id: Int? = null
    private val presenter = EditClothesPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user_id = requireArguments().getInt("user_id")
        return inflater.inflate(R.layout.fragment_editclothes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btEditClothes.setOnClickListener {
            presenter.editClothes((activity as AddEditClothesActivity?)!!.getInformation())
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("user_id", user_id)
            intent.putExtra("CURR_FRAG", "CLOSET")
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

        }
    }
}