package com.example.rewear.editClothes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rewear.MainActivity

import com.example.rewear.R
import com.example.rewear.addEditClothes.AddEditClothesActivity
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
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
        btEditClothes.setOnClickListener {
            (activity as AddEditClothesActivity?)!!.getCategories()
            val newTags: List<ClothesCategoryData>? = (activity as AddEditClothesActivity?)!!.getNewTags()
            val existingTags: List<ClothesBelongsToData>? = (activity as AddEditClothesActivity?)!!.getExistingTags()
            val deletedTags: List<ClothesBelongsToData> =  (activity as AddEditClothesActivity?)!!.getDeleted()
            val clothes = (activity as AddEditClothesActivity?)!!.getInformation()

            presenter.deleteFromTags(deletedTags)
            presenter.addNewTags(newTags, clothes)
            presenter.addToTags(existingTags)
            presenter.editClothes(clothes)


            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("user_id", user_id)
            intent.putExtra("CURR_FRAG", "CLOSET")
            startActivity(intent)
        }
        btDeleteClothes.setOnClickListener{

        }

    }
}