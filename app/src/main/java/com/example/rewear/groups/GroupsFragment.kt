package com.example.rewear.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.objects.PictureData
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment(), GroupsContract.View {
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

        //Edit Expenses
        val onClickListener: GroupsFragment.OnClickListener = position {
            //NEED STORE LIKES TO DATABSE
        };

        //Set up recycler view
        groupsAdaptorClass = GroupsAdaptorClass(onClickListener);
        rvExpense.setAdapter(ListExpenseAdapterClass);
        rvExpense.setLayoutManager(new LinearLayoutManager(this));
        presenter.LoadExpense();


        val r = Runnable { print("Run method") }

    }




    //Handles on receive expense data
    override fun onRecieveData(pictures: List<PictureData>?) {
        //Populate the adapter
        /*ListExpenseAdapterClass.setExpense(expenses);
        ListExpenseAdapterClass.notifyDataSetChanged();
        alExpenses = expenses;*/
    }
}
