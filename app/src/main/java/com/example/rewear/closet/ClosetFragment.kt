package com.example.rewear.groups

//import kotlinx.android.synthetic.main.activity_group_contraintlayout.*

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rewear.R
import com.example.rewear.addEditClothes.AddEditClothesActivity
import com.example.rewear.closet.ClosetContract
import com.example.rewear.closet.ClosetPresenter
import com.example.rewear.closetAdaptor.ClosetAdaptorClass
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_closet.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.droidsonroids.gif.GifImageView
import java.io.Serializable


class ClosetFragment : Fragment(), ClosetContract.View {
    private val presenter = ClosetPresenter(this)
    var userID: Int? = null
    private var closetAdaptor: ClosetAdaptorClass? = null
    var rvCloset: RecyclerView? = null
    var dropDownData: List<ClothesCategoryData>? = null
    var dialog: Dialog? = null
    var adapter: ArrayAdapter<String>? = null
    private var progressBar: GifImageView? = null
    var names: List<String?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userID = requireArguments().getInt("user_id")
        presenter.getCategories(userID!!)

        return inflater.inflate(R.layout.fragment_closet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeVals()

        addPicture.setOnClickListener {
            val intent = Intent(activity, AddEditClothesActivity::class.java)
            val args = Bundle()
            args.putSerializable("ARRAYLIST", dropDownData as Serializable?)
            intent.putExtra("BUNDLE", args)
            intent.putExtra("screenDisplay", 1)
            intent.putExtra("OBJECT", ClothesData(userID))
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)
        }

        if (spinnerCloset.text == "Search Tags") {
            noneSelected.visibility = View.VISIBLE
        } else {
            noneSelected.visibility = View.GONE
        }

        spinnerCloset.setOnClickListener(View.OnClickListener {


            dialog = activity?.let { Dialog(it, android.R.style.Theme_Black_NoTitleBar_Fullscreen) }
            dialog!!.setCanceledOnTouchOutside(true) //close when click outside of screen
            // set custom dialog
            dialog!!.setContentView(R.layout.dialog_searchable_spinner)

            //setting background
            val window: Window = dialog!!.window!!
            val windowParams: WindowManager.LayoutParams = window.attributes
            windowParams.dimAmount = 0.70f
            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND

            // set custom height and width
            val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
            val height = (resources.displayMetrics.heightPixels * 0.90).toInt()
            dialog!!.window!!.setLayout(width, height)

            //setting it to the window
            window.attributes = windowParams

            // set transparent background so it doesn't cover page
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // show dialog
            dialog!!.show()
            // set adapter

            //reset any past searching
            adapter!!.filter.filter("")

            val editText: EditText = dialog!!.findViewById(R.id.search_bar)
            val listView: ListView = dialog!!.findViewById(R.id.list_view)
            listView.adapter = adapter



            //searchBar
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    adapter!!.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable) {}
            })

            listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, id -> // when item selected from list

                    val stirng = adapter!!.getItem(id.toInt())
                    // set selected item on textView
                    view.rootView.spinnerCloset.text = stirng


                    val searchPosition: Int = names!!.indexOf(stirng)

                    //hide recyclar view with all the cards
                    view.rootView.findViewById<RecyclerView>(R.id.recycler_view).visibility =
                        View.INVISIBLE
                    // Dismiss dialog
                    dialog!!.dismiss()

                    //showing loading bar
                    progressBar = view.rootView.findViewById(R.id.loading)
                    progressBar?.visibility = View.VISIBLE

                    //reset any past searching

                    CoroutineScope(Dispatchers.IO).launch {


                        //setting the data in the closet adaptor class
                        closetAdaptor!!.setData(
                            dropDownData!![searchPosition].category_id!!
                        )


                        CoroutineScope(Dispatchers.Main).launch {
                            closetAdaptor!!.notifyDataSetChanged()

                            //hiding loading bar
                            progressBar = view.rootView.findViewById(R.id.loading)
                            progressBar?.visibility = View.INVISIBLE
                            view.rootView.findViewById<RecyclerView>(R.id.recycler_view).visibility =
                                View.VISIBLE

                        }


                    }
                }
        })
    }


    override fun returnGetCategories(clothesCategories: List<ClothesCategoryData>?) {
        dropDownData = clothesCategories
    }

    private fun initializeVals() {
        // Initialize array adapter
        names = dropDownData!!.map { it.name }
        adapter = activity?.let { it1 ->
            ArrayAdapter<String>(
                it1,
                android.R.layout.simple_dropdown_item_1line, names!!
            )
        }
        //controls
        rvCloset = view?.findViewById(R.id.recycler_view)
        closetAdaptor = ClosetAdaptorClass(dropDownData!!)
        rvCloset?.adapter = closetAdaptor
        rvCloset!!.layoutManager = LinearLayoutManager(activity)
    }


}
