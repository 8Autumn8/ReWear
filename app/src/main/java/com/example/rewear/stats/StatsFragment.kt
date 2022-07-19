package com.example.rewear.stats

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.objects.ClothesData


class StatsFragment : Fragment(), StatsContract.View  {
    private lateinit var presenter: StatsContract.Presenter
    var user_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = StatsPresenter(this)
        user_id = Integer.parseInt(requireArguments().getString("user_id")!!)
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ends function if mostWorn clothes was not found...
        val mostWorn: ClothesData? = presenter.getMostWorn(Integer.parseInt(requireArguments().getString("user_id").toString())) ?: return
        updateMostWornCard(view, mostWorn!!)
    }

    private fun updateMostWornCard(view: View, clothesData: ClothesData) {
        // Don't update if given clothesData does not contain an image.
        if (clothesData.clothes_pic == null) return

        // Update the image
        var img = view.findViewById<ImageView>(R.id.mostWornImage)
        var bitmap = BitmapFactory.decodeByteArray(clothesData.clothes_pic, 0, clothesData.clothes_pic!!.size)
        img.setImageBitmap(bitmap)

        // Update the image description
        view.findViewById<TextView>(R.id.mostWornImageDesc).text = clothesData.clothes_desc
    }

    /*
     * I know I could've created the CardViews in fragment_stats.xml but I really
     * don't want to spend more time on this than I already have. I'll save it for
     * a future project, if I ever continue app development.
     *      -- Minh Nguyen
     */
}
