package com.example.rewear.groups

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.objects.PictureData


class GroupsAdaptorClass : RecyclerView.Adapter<GroupsAdaptorClass.ViewHolder>() {


    var pictures: List<PictureData>? = null

    //Recycler view onClick
    interface OnClickListener {
        fun constructor(position: Int)
    }

     fun GroupsAdaptorClass(onClickListener: GroupsAdaptorClass.OnCLickListener ){

    }

    fun setPictureData(given: List<PictureData>?){ this.pictures = given}


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupsAdaptorClass.ViewHolder {
        //Use layout inflator to inflate a view
        val expenses: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_groups_layout, parent, false)

        //wrap it inside a View Holder and return it
        return RecyclerView.ViewHolder(pictures)
    }

    //Bind the data to recyclerview
    override fun onBindViewHolder(holder: GroupsAdaptorClass.ViewHolder, position: Int) {
        //Grab the expense at the position
        val expense: PictureData = pictures!!.get(position)
        //Bind the expense into the View Holder
        holder.bind(expense)
    }

    //Get Item count
    override fun getItemCount(): Int {
        return pictures!!.size
    }


    //Container to provide easy access to views that represent each row of the list
    internal class ViewHolder(expenseView: View) : RecyclerView.ViewHolder(expenseView) {
        //Declare controls inside of the View Holder
        var tvDate: TextView
        var tvPayee: TextView
        var tvAmount: TextView
        var tvCategory: TextView

        //Update the view inside of the View Holder with this data
        /*fun bind(picture: PictureData) {
            tvDate.setText(picture.getDate())
            tvPayee.setText(picture.getPayee())
            tvAmount.setText(picture.getAmount())
            tvCategory.setText(picture.getCategory())
            tvDate.setOnClickListener { v: View? ->
                onClickListener.OnClickListener(
                    getBindingAdapterPosition()
                )
            }
        }*/

        init {
            //Initialize the controls inside of the View Holder
            tvDate = expenseView.findViewById(R.id.tvDate)
            tvPayee = expenseView.findViewById(R.id.tvPayee)
            tvAmount = expenseView.findViewById(R.id.tvAmount)
            tvCategory = expenseView.findViewById(R.id.tvCategory)
        }
    }
}