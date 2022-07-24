package com.example.rewear.stats

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.objects.ClothesData
import kotlinx.android.synthetic.main.fragment_stats.*


class StatsFragment : Fragment(), StatsContract.View {
    private lateinit var presenter: StatsContract.Presenter
    private var mostWorn: ClothesData? = null
    private var percentWornLastWeek: Int? = null
    private var percentWornLastMonth: Int? = null
    private var userID: Int? = null
    private val DESC_MAX_LENGTH = 64

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = StatsPresenter(this)
        userID = requireArguments().getInt("user_id")
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // If user_id is still null somehow, then send a toast and die
        if (userID == null) {
            Toast.makeText(
                context,
                "Error retrieving user from database. Try again later",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // ends function if mostWorn clothes was not found...
        presenter.getMostWorn(userID!!)

        // Gets the percentage of closet worn from date...
        presenter.getPercentageWornFromDate(userID!!)


        if (percentWornLastWeek != null && percentWornLastMonth != null) {
            // update most worn card
            updateMostWornCard(view, mostWorn!!)
            updatePercentageCards()
        }

        // hide loading icon, and show the info cards
        statsLoadingIcon.visibility = View.GONE

        percentWornLastWeekCard.visibility = View.VISIBLE
        percentWornLastMonthCard.visibility = View.VISIBLE
        mostWornCard.visibility = View.VISIBLE
    }

    private fun updatePercentageCards() {
        // update the progress bars
        percentWornLastWeekProgress.progress = percentWornLastWeek!!
        percentWornLastMonthProgress.progress = percentWornLastMonth!!

        percentWornLastWeekProgressText.text = "$percentWornLastWeek"
        percentWornLastMonthProgressText.text = "$percentWornLastMonth"
    }

    private fun updateMostWornCard(clothesData: ClothesData) {
        // Don't update if given clothesData does not contain an image.
        if (clothesData.clothes_pic == null) return

        // Update the image
        val img = mostWornImage
        val bitmap =
            BitmapFactory.decodeByteArray(clothesData.clothes_pic, 0, clothesData.clothes_pic!!.size)
        img.setImageBitmap(bitmap)

        // Update the image description
        var desc = clothesData.clothes_desc

        // makes sure clothing description isn't too long
        if (clothesData.clothes_desc!!.length > DESC_MAX_LENGTH)
            desc = clothesData.clothes_desc!!.substring(0, DESC_MAX_LENGTH)

        mostWornImageDesc.text = desc
    }

    override fun returnMostWorn(mostWorn: ClothesData?) {
        this.mostWorn = mostWorn
    }

    override fun returnPercentageWornFromDate(percentLastWeek: Int, percentLastMonth: Int) {
        percentWornLastWeek = percentLastWeek
        percentWornLastMonth = percentLastMonth
    }
    /*
     * I know I could've created the CardViews in fragment_stats.xml but I really
     * don't want to spend more time on this than I already have. I'll save it for
     * a future project, if I ever continue app development.
     *      -- Minh Nguyen
     */
}
