package com.example.rewear

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.objects.ClothesData
import org.w3c.dom.Text

class ClosetAdaptorClass : RecyclerView.Adapter<ClosetAdaptorClass.ViewHolder>(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    private var clothesData: List<ClothesData>? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clothingPicture: ImageView
        var txtDateCreated: TextView
        var txtDescription: TextView
        var txtLastWorn: TextView
        var categoriesBelongTo: TextView

        init {
            clothingPicture = itemView.findViewById(R.id.clothingPicture)
            txtDateCreated = itemView.findViewById(R.id.txtDateCreated)
            txtDescription = itemView.findViewById(R.id.txtDescription)
            txtLastWorn = itemView.findViewById(R.id.txtLastWorn)
            categoriesBelongTo = itemView.findViewById(R.id.txtClothesBelongsTo)

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

            viewHolder.txtDateCreated.text = clothesData!![i].date_created
            viewHolder.txtDescription.text = clothesData!![i].clothes_desc
            //viewHolder.txtLastWorn.txt = clothesData!![i].lastWorn

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

