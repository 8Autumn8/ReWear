package com.example.rewear.groups

//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.ClosetAdaptorClass
import com.example.rewear.R
import com.example.rewear.addEditClothes.AddEditClothesActivity
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_closet.addPicture
import kotlinx.android.synthetic.main.fragment_groups.*
import kotlinx.android.synthetic.main.fragment_groups.description
import java.io.Serializable


class ClosetFragment : Fragment(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    var user_id: Int? = null
    private var closetAdaptor: ClosetAdaptorClass? = null
    var rvCloset: RecyclerView? = null
    var dropDownData: List<ClothesCategoryData>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        user_id = requireArguments().getInt("user_id")
        dropDownData = presenter.getCategories(user_id!!)

        return inflater.inflate(com.example.rewear.R.layout.fragment_closet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeControls()
        createSpinnerList()
        manageSpinner()

        addPicture.setOnClickListener {
            val intent = Intent(activity, AddEditClothesActivity::class.java)
            val args = Bundle()
            args.putSerializable("ARRAYLIST", dropDownData as Serializable?)
            intent.putExtra("BUNDLE", args)
            intent.putExtra("screenDisplay", 1)
            intent.putExtra("OBJECT", ClothesData(user_id))
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(outState)
    }

    fun manageSpinner() {
        loadingIcon.visibility = View.VISIBLE
        spinnerCloset.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                if (spinnerCloset.selectedItemPosition != 0) {
                    noneSelected.visibility = View.GONE
                    description.text =
                        dropDownData!![spinnerCloset.selectedItemPosition-1].description

                    closetAdaptor!!.setData(
                        dropDownData!![spinnerCloset.selectedItemPosition-1].category_id!!
                    )
                    closetAdaptor!!.notifyDataSetChanged()
                } else {
                    noneSelected.visibility = View.VISIBLE
                }
                    loadingIcon.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    fun initializeControls() {
        rvCloset = view?.findViewById(R.id.recycler_view)

        closetAdaptor = ClosetAdaptorClass(dropDownData!!)
        rvCloset?.adapter = closetAdaptor
        rvCloset!!.layoutManager = LinearLayoutManager(activity)
    }

    fun createSpinnerList() {
        loadingIcon.visibility = View.VISIBLE
        var strCategory =
            arrayOfNulls<String?>(dropDownData!!.size+1) //{"Select Category", "Category 1", "Category 2", "Category 3"};
        strCategory[0] = "Select Category"
        for (i in 1..dropDownData!!.size) strCategory[i] = dropDownData!![i-1].name

        val adCategory = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            strCategory
        )

        spinnerCloset.adapter = adCategory
    }
}
