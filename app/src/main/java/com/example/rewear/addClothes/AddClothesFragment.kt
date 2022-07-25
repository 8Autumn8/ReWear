package com.example.rewear.addClothes

import android.content.Intent
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
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import kotlinx.android.synthetic.main.fragment_addclothes.*

class AddClothesFragment : Fragment(), AddClothesContract.View {
    var user_id: Int? = null
    private val presenter = AddClothesPresenter(this)
    private var clothesID: Int? = null


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
            (activity as AddEditClothesActivity?)!!.getCategories()
            val newTags: List<ClothesCategoryData>? = (activity as AddEditClothesActivity?)!!.getNewTags()
            val existingTags: List<ClothesBelongsToData>? = (activity as AddEditClothesActivity?)!!.getExistingTags()
            val clothes = (activity as AddEditClothesActivity?)!!.getInformation()


            presenter.addClothes(clothes)
            presenter.addNewTags(newTags, user_id!!, clothesID!!)
            presenter.addToTags(existingTags, clothesID!!)



            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("user_id", user_id)
            intent.putExtra("CURR_FRAG", "CLOSET")
            startActivity(intent)
        }
    }

    override fun setClothesID(id: Int){
        clothesID = id
    }
}

