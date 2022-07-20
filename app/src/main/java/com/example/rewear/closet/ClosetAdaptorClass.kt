package com.example.rewear

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.addEditClothes.AddEditClothesActivity
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import java.io.Serializable


class ClosetAdaptorClass(val categories: List<ClothesCategoryData>) :
    RecyclerView.Adapter<ClosetAdaptorClass.ViewHolder>(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    private var clothesData: List<ClothesData>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clothingPicture: ImageView
        var txtDateCreated: TextView
        var txtDescription: TextView
        var txtLastWorn: TextView
        var categoriesBelongTo: TextView
        var txtTotalDaysWorn: TextView

        init {
            clothingPicture = itemView.findViewById(R.id.clothingPicture)
            txtDateCreated = itemView.findViewById(R.id.txtDateCreated)
            txtDescription = itemView.findViewById(R.id.txtDescription)
            txtLastWorn = itemView.findViewById(R.id.txtLastWorn)
            categoriesBelongTo = itemView.findViewById(R.id.txtClothesBelongsTo)
            txtTotalDaysWorn = itemView.findViewById(R.id.txtTotalDaysWorn)

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                launchEditClothes(itemView.context, position)

            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_closet, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        if (clothesData!![i].clothes_pic != null) {
            val blob: ByteArray = clothesData!![i].clothes_pic!!

            viewHolder.txtDateCreated.text = clothesData!![i].date_created
            viewHolder.txtDescription.text = clothesData!![i].clothes_desc
            viewHolder.txtLastWorn.text = clothesData!![i].last_worn
            viewHolder.categoriesBelongTo.text = clothesData!![i].category_name
            viewHolder.txtTotalDaysWorn.text = clothesData!![i].total_days_worn.toString()
            viewHolder.clothingPicture.setImageBitmap(
                BitmapFactory.decodeByteArray(blob, 0, blob!!.size)
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
    fun setData(clothesCategoryData: Int?) {
        clothesData = presenter.getPicturesByCategory(clothesCategoryData)

    }

    fun launchEditClothes(context:Context, position: Int){
        val intent = Intent(context, AddEditClothesActivity::class.java)

        intent.putExtra("OBJECT", clothesData!![position])
        intent.putExtra("screenDisplay", 0)
        val args = Bundle()
        args.putSerializable("ARRAYLIST", categories as Serializable?)
        intent.putExtra("BUNDLE", args)
        context.startActivity(intent)
    }

}

