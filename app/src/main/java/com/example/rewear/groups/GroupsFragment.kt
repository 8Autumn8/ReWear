package com.example.rewear.groups

//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.GroupsAdaptorClass
import com.example.rewear.objects.GroupsData
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment(), GroupsContract.View{
    private val presenter = GroupsPresenter(this)
    var id: Int? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<GroupsAdaptorClass.ViewHolder>? = null
    //var recyclerView = requireActivity().findViewById(com.example.rewear.R.id.recycler_view) as RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        id = Integer.parseInt(requireArguments().getString("user_id"))
        return inflater.inflate(com.example.rewear.R.layout.fragment_groups, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var dropDownData: List<GroupsData>? = presenter.getGroups(id!!)


        //pop up menu slide

        //********************create a list of items for the Category Spinner
        var strCategory =
            arrayOfNulls<String?>(dropDownData!!.size + 1) //{"Select Category", "Category 1", "Category 2", "Category 3"};

        strCategory[0] = "Select Group"
        for (i in 1..dropDownData!!.size) strCategory[i] = dropDownData!![i - 1].group_name

        val adCategory = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, strCategory)
        adCategory.notifyDataSetChanged()
        spinnerGroups.adapter = adCategory
//*************************************************
        spinnerGroups.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long) {

                //adapter!!.notifyDataSetChanged()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = GroupsAdaptorClass()
        }
    }

    //Populate the Category dropdown



}
