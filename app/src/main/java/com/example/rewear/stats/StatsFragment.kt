package com.example.rewear.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rewear.R


class StatsFragment : Fragment(), StatsContract.View  {
    private var presenter: StatsContract.Presenter? = null
    var user_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = StatsPresenter(this)
        user_id = Integer.parseInt(requireArguments().getString("user_id"))
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
