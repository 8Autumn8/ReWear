package com.example.rewear.groups

import android.graphics.Picture
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.rewear.R
import com.example.rewear.objects.PictureData


class GroupsFragment : Fragment(), GroupsContract.View  {
    private var presenter: GroupsContract.Presenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = GroupsPresenter(this)
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    //Handles on receive expense data
     override fun onRecieveData(pictures: List<PictureData>?){
        //Populate the adapter
        /*ListExpenseAdapterClass.setExpense(expenses);
        ListExpenseAdapterClass.notifyDataSetChanged();
        alExpenses = expenses;*/
    }
}
