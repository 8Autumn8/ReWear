package com.example.rewear.stats

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.objects.ClothesData
import java.util.*


class StatsFragment : Fragment(), StatsContract.View {
    private lateinit var presenter: StatsContract.Presenter
    private var user_id: Int? = null
    private var WEEK = 604800000L
    private var MONTH = 2629800000L
    private val DESC_MAX_LENGTH = 64

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
        // If user_id is still null somehow, then send a toast and die
        if (user_id == null) {
            Toast.makeText(
                context,
                "Error retrieving user from database. Try again later",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // ends function if mostWorn clothes was not found...
        val mostWorn: ClothesData = presenter.getMostWorn(user_id!!) ?: return
        updateMostWornCard(view, mostWorn)

        // Gets the percentage of closet worn from date...
        val percentWornLastWeek = presenter.getPercentageWornFromDate(user_id!!, java.sql.Date(Date().time - WEEK))
        val percentWornLastMonth = presenter.getPercentageWornFromDate(user_id!!, java.sql.Date(Date().time - MONTH))

        // update the progress bars
        view.findViewById<ProgressBar>(R.id.percentWornLastWeekProgress).progress = percentWornLastWeek
        view.findViewById<ProgressBar>(R.id.percentWornLastMonthProgress).progress = percentWornLastMonth

        view.findViewById<TextView>(R.id.percentWornLastWeekProgressText).text = "$percentWornLastWeek"
        view.findViewById<TextView>(R.id.percentWornLastMonthProgressText).text = "$percentWornLastMonth"
    }

    private fun updateMostWornCard(view: View, clothesData: ClothesData) {
        // Don't update if given clothesData does not contain an image.
        if (clothesData.clothes_pic == null) return

        // Update the image
        val img = view.findViewById<ImageView>(R.id.mostWornImage)
        val bitmap =
            BitmapFactory.decodeByteArray(clothesData.clothes_pic, 0, clothesData!!.clothes_pic!!.size)
        img.setImageBitmap(bitmap)

        // Update the image description
        var desc = clothesData.clothes_desc
        if (clothesData.clothes_desc!!.length > DESC_MAX_LENGTH)
            desc = clothesData!!.clothes_desc!!.substring(0, DESC_MAX_LENGTH)

        view.findViewById<TextView>(R.id.mostWornImageDesc).text = desc
    }

    /*
     * I know I could've created the CardViews in fragment_stats.xml but I really
     * don't want to spend more time on this than I already have. I'll save it for
     * a future project, if I ever continue app development.
     *      -- Minh Nguyen
     */
}
