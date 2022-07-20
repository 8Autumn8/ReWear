package com.example.rewear.groups

//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.GroupsAdaptorClass
import com.example.rewear.R
import com.example.rewear.objects.GroupsData
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment(), GroupsContract.View{
    private val presenter = GroupsPresenter(this)
    var id: Int? = null
    private var groupsAdaptor: GroupsAdaptorClass? = null
    var rvGroups: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        id = requireArguments().getInt("user_id")
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dropDownData: List<GroupsData>? = presenter.getGroups(id!!)


        //Initialize controls
        rvGroups = getView()?.findViewById(R.id.recycler_view)

        groupsAdaptor = GroupsAdaptorClass()
        rvGroups?.adapter = groupsAdaptor
        rvGroups?.layoutManager = LinearLayoutManager(activity)

        //pop up menu slide
        //********************create a list of items for the Category Spinner
        var strCategory =
            arrayOfNulls<String?>(dropDownData!!.size + 1) //{"Select Category", "Category 1", "Category 2", "Category 3"};

        strCategory[0] = "Select Group"
        for (i in 1..dropDownData!!.size) strCategory[i] = dropDownData!![i - 1].group_name

        val adCategory = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, strCategory)

        spinnerGroups.adapter = adCategory
//************************************************

        spinnerGroups.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {

                if(spinnerGroups.selectedItem.toString() != "Select Group"){
                    description.text = dropDownData[spinnerGroups.selectedItemPosition-1].group_desc
                    groupsAdaptor!!.setData(dropDownData[spinnerGroups.selectedItemPosition-1].group_id,id.toInt())
                    groupsAdaptor!!.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}





