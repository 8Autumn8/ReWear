package com.example.rewear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.groups.GroupsContract
import com.example.rewear.groups.GroupsPresenter
import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.LikedData
import com.example.rewear.objects.PictureData

class ClosetAdaptorClass : RecyclerView.Adapter<ClosetAdaptorClass.ViewHolder>(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    private var clothesData: List<ClothesData>? = null
    private var likeData: List<LikedData>? = null

    private val kode: MutableList<String>? = mutableListOf()

    private val kategori: MutableList<String>? = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemKode: TextView
        var itemKategori: TextView

        init {
            itemKode = itemView.findViewById(R.id.kodePertanyaan)
            itemKategori = itemView.findViewById(R.id.kategori)

            itemView.setOnClickListener {
                /* position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, DetailPertanyaan::class.java).apply {
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

        viewHolder.itemKode.text = kode!![i]!!
        viewHolder.itemKategori.text = kategori!![i]

    }

    override fun getItemCount(): Int {
        return likeData!!.size
    }

    //code to update recyclar view
    fun setData(group_id: Int?, user_id: Int?){
        if (group_id != -1){
            //likeData = presenter.getUserLiked(user_id!!, group_id!!)
            //pictureData = presenter.getPictures(group_id)
            kode!!.add("kode1$group_id")
            kode!!.add("kode2$group_id")
            kode!!.add("kode3$group_id")
            kode!!.add("kode4$group_id")
            kode!!.add("kode5$group_id")
            kode!!.add("kode6$group_id")
            kode!!.add("kode7$group_id")
            kode!!.add("kode8$group_id")

            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
            kategori!!.add("kategori$group_id")
        }
    }


}

