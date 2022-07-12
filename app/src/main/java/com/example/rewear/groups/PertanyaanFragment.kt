package com.example.rewear

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.R
import com.example.rewear.RecyclerAdapter
import kotlinx.android.synthetic.main.card_pertanyaan.view.*
//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*
import kotlinx.android.synthetic.main.fragment_pertanyaan.*


class PertanyaanFragment : Fragment() {

    private var layoutManage: RecyclerView.LayoutManager? = null
    private var adapt: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pertanyaan, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManage = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapt = RecyclerAdapter()
        }
    }
}