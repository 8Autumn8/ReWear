package com.example.rewear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.groups.GroupsContract
import com.example.rewear.groups.GroupsPresenter
import com.example.rewear.objects.PictureData

class GroupsAdaptorClass : RecyclerView.Adapter<GroupsAdaptorClass.ViewHolder>(), GroupsContract.View {
    private val presenter = GroupsPresenter(this)
    private var data: List<PictureData>? = null


    private val kode = arrayOf("d116df5")

    private val kategori = arrayOf("Kekayaan")

    private val isi = arrayOf("pertanyaan 9")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemKode: TextView
        var itemKategori: TextView

        init {
            itemKode = itemView.findViewById(R.id.kodePertanyaan)
            itemKategori = itemView.findViewById(R.id.kategori)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                /*val intent = Intent(context, DetailPertanyaan::class.java).apply {
                val intent = Intent(context, DetailPertanyaan::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("CODE", itemKode.text)
                    putExtra("CATEGORY", itemKategori.text)
                    putExtra("CONTENT", itemIsi.text)
                }
                context.startActivity(intent)*/
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_groups, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemKode.text = data!![i]!!.username
        viewHolder.itemKategori.text = kategori[i]

    }

    override fun getItemCount(): Int {
        return kode.size
    }

    fun setPictures(group_id: Int){
        data = presenter.getPictures(1)
    }
}

