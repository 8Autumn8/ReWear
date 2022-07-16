package com.example.rewear

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.objects.ClothesData

class ClosetAdaptorClass : RecyclerView.Adapter<ClosetAdaptorClass.ViewHolder>(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    private var clothesData: List<ClothesData>? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clothingPicture: ImageView

        init {
            clothingPicture = itemView.findViewById(R.id.clothingPicture)

            itemView.setOnClickListener {
                /* position: Int = getAdapterPosition()
                val context = itemView.context
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
            .inflate(R.layout.card_closet, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        if (clothesData!![i].clothes_pic != null){
            val blob: ByteArray = clothesData!![i].clothes_pic!!
            viewHolder.clothingPicture.setImageBitmap( BitmapFactory.decodeByteArray(blob, 0, blob!!.size)
            )

        }


    }

    override fun getItemCount(): Int {
        if (clothesData != null) {
            return clothesData!!.size
        }
        return 0

    }

    //code to update recyclar view
    fun setData(clothesCategoryData: Int){
        if (clothesCategoryData != -1){
            clothesData = presenter.getPictures(clothesCategoryData)

        }
    }


}

