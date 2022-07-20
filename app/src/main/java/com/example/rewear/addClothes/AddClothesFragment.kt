package com.example.rewear.addClothes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rewear.MainActivity
import com.example.rewear.R
import com.example.rewear.addEditClothes.AddClothesContract
import com.example.rewear.addEditClothes.AddClothesPresenter
import com.example.rewear.addEditClothes.AddEditClothesActivity
import kotlinx.android.synthetic.main.activity_add_edit_clothes.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_addclothes.*
import java.io.ByteArrayOutputStream

class AddClothesFragment : Fragment(), AddClothesContract.View {
    var user_id: Int? = null
    private val presenter = AddClothesPresenter(this)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user_id = requireArguments().getInt("user_id")
        return inflater.inflate(R.layout.fragment_addclothes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btAddClothes.setOnClickListener {
            presenter.addClothes((activity as AddEditClothesActivity?)!!.getInformation())
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("user_id", user_id)
            intent.putExtra("CURR_FRAG", "CLOSET")
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

        }
    }
}

