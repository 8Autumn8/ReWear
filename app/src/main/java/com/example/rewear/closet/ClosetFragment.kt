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
import com.example.rewear.GroupsAdaptorClass
import com.example.rewear.R
import com.example.rewear.addClothes.AddClothesActivity
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.objects.ClothesCategoryData
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_groups.description


class ClosetFragment : Fragment(), ClosetContract.View{
    private val presenter = ClosetPresenter(this)
    var id: Int? = null
    private var closetAdaptor: ClosetAdaptorClass? = null
    var rvCloset: RecyclerView? = null
    var btnAddPicture: Button? =  null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(com.example.rewear.R.layout.fragment_closet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        id = Integer.parseInt(requireArguments().getString("user_id"))

        var dropDownData: List<ClothesCategoryData>? = presenter.getCategories(id!!)

        btnAddPicture = getView()?.findViewById<Button>(R.id.addPicture)
        btnAddPicture?.setOnClickListener{
            val intent = Intent(activity, AddClothesActivity::class.java)
            intent.putExtra("user_id", id.toString())
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)
        }


        //Initialize controls
        rvCloset = getView()?.findViewById<RecyclerView>(com.example.rewear.R.id.recycler_view)

        closetAdaptor = ClosetAdaptorClass()
        rvCloset?.adapter = closetAdaptor
        rvCloset!!.layoutManager = LinearLayoutManager(activity)

        //********************create a list of items for the Category Spinner
        var strCategory =
            arrayOfNulls<String?>(dropDownData!!.size + 1) //{"Select Category", "Category 1", "Category 2", "Category 3"};

        strCategory[0] = "All"
        for (i in 1..dropDownData!!.size) strCategory[i] = dropDownData!![i - 1].name

        val adCategory = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, strCategory)

        spinnerCloset.adapter = adCategory
//************************************************

        spinnerCloset.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {

                if(spinnerCloset.selectedItem.toString() != "All"){
                    description.text = dropDownData[spinnerCloset.selectedItemPosition-1].description
                    closetAdaptor!!.setData(dropDownData[spinnerCloset.selectedItemPosition-1].category_id!!)
                    closetAdaptor!!.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


    }
}
