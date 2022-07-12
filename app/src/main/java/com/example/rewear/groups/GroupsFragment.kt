package com.example.rewear.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.GroupsAdaptorClass
import com.example.rewear.R
import com.example.rewear.objects.PictureData
//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*
import kotlinx.android.synthetic.main.fragment_groups.*
import kotlinx.android.synthetic.main.fragment_pertanyaan.*


class GroupsFragment : Fragment(), GroupsContract.View {
    private var presenter: GroupsContract.Presenter? = null
    private var layManager: RecyclerView.LayoutManager? = null
    private var adapt: RecyclerView.Adapter<GroupsAdaptorClass.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = GroupsPresenter(this)
        return inflater.inflate(R.layout.fragment_pertanyaan, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapt = GroupsAdaptorClass()
        }
    }




    //Handles on receive expense data
    override fun onRecieveData(pictures: List<PictureData>?) {
        //Populate the adapter
        /*ListExpenseAdapterClass.setExpense(expenses);
        ListExpenseAdapterClass.notifyDataSetChanged();
        alExpenses = expenses;*/
    }
}
