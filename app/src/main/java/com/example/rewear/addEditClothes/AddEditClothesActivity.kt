package com.example.rewear.addEditClothes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.*
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.rewear.addClothes.AddClothesFragment
import com.example.rewear.editClothes.EditClothesFragment
import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.ClothesData
import kotlinx.android.synthetic.main.activity_add_edit_clothes.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.time.LocalDateTime
import java.util.*


class AddEditClothesActivity() : AppCompatActivity() {
    private var ibCamara: ImageButton? = null
    private var imageView: ImageView? = null
    private var tempImagesDir: File? = null
    private var screenDisplay: Int? = null //1 = addClothes, 0 = editClothes
    private var contentFragment: Fragment? = null
    private var clothesData: ClothesData? = null
    private var categories: List<ClothesCategoryData>? = null
    private var dateAdded: TextView? = null
    private var description: TextView? = null
    private var checkBox: CheckBox? = null
    private var adapter: ArrayAdapter<String>? = null
    private var names: List<String>? = null
    private var autoCompleteView: MultiAutoCompleteTextView? = null
    private var deletedIDs: MutableList<ClothesBelongsToData> = mutableListOf()
    private var listOfTagsID: MutableList<ClothesBelongsToData> = mutableListOf()
    private var listOfNewTags: MutableList<ClothesCategoryData> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.rewear.R.layout.activity_add_edit_clothes)

        initializeViews()

        val bundle = Bundle()

        bundle.putInt("user_id", clothesData!!.user_id!!)
        if (screenDisplay == 1) {
            addClothes(bundle)
        } else if (screenDisplay == 0) {
            editClothes(bundle)
            fillScreen()
        }
        contentFragment!!.arguments = bundle

        // initialize temp image directory on first launch
        val tempImageUri = initTempUri()

        registerTakePictureLauncher(tempImageUri) //Binds button to launch camera activity

        adaptor()
    }

    private fun initTempUri(): Uri {
        //gets the temp_images dir
        tempImagesDir = File(
            applicationContext.filesDir, //this function gets the external cache dir
            getString(com.example.rewear.R.string.temp_images_dir)
        ) //gets the directory for the temporary images dir

        tempImagesDir!!.mkdir() //Create the temp_images dir

        //Creates the temp_image.jpg file
        val tempImage = File(
            tempImagesDir, //prefix the new abstract path with the temporary images dir path
            getString(com.example.rewear.R.string.temp_image)
        ) //gets the abstract temp_image file name

        //Returns the Uri object to be used with ActivityResultLauncher
        return FileProvider.getUriForFile(
            applicationContext,
            getString(com.example.rewear.R.string.authorities),
            tempImage
        )
    }


    private fun registerTakePictureLauncher(path: Uri) {

        //Creates the ActivityResultLauncher
        val resultLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            imageView!!.setImageURI(null) //rough handling of image changes. Real code need to handle different API levels.
            imageView!!.setImageURI(path)
        }

        //Launches the camera when button is pressed.
        ibCamara!!.setOnClickListener {
            resultLauncher.launch(path) //launches the activity here
        }


    }


    private fun addClothes(bundle: Bundle) {
        contentFragment = AddClothesFragment()
        contentFragment!!.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(com.example.rewear.R.id.container, contentFragment!!).commit()
        dateAdded!!.text = dateAdded!!.text.toString() + Calendar.getInstance().time

    }

    private fun editClothes(bundle: Bundle) {
        contentFragment = EditClothesFragment()
        contentFragment!!.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(com.example.rewear.R.id.container, contentFragment!!).commit()
    }

    fun getInformation(): ClothesData {
        var img: ByteArray? = null
        val desc: String = description!!.text.toString()
        if (imageView?.drawable != null) {
            val bmTemp: Bitmap = (ivClothingItem!!.drawable as BitmapDrawable).bitmap
            val bos = ByteArrayOutputStream()
            bmTemp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            img = bos.toByteArray()
        }
        if (checkBox!!.isChecked()) {
            clothesData!!.last_worn = Calendar.getInstance().time.toString()
        } else {
            clothesData!!.last_worn = null
        }

        clothesData!!.clothes_desc = desc
        clothesData!!.clothes_pic = img
        return clothesData as ClothesData
    }

    private fun adaptor() {
        names = categories!!.map { it.name!! }
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, names!!
        )

        autoCompleteView!!.setAdapter(adapter)
        autoCompleteView!!.setTokenizer(CommaTokenizer())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fillScreen() {

        val bmp = BitmapFactory.decodeByteArray(
            clothesData!!.clothes_pic,
            0,
            clothesData!!.clothes_pic!!.size
        )
        imageView!!.setImageBitmap(bmp)
        description!!.text = clothesData!!.clothes_desc
        findViewById<MultiAutoCompleteTextView>(com.example.rewear.R.id.autoComplete).text =
            SpannableStringBuilder(clothesData!!.category_name + ", ")
        dateAdded!!.text = dateAdded!!.text.toString() + clothesData!!.date_created
        if (clothesData!!.last_worn == LocalDateTime.now().toString()) {
            checkBox!!.isChecked
        }
    }

    private fun initializeViews() {
        autoCompleteView = findViewById(com.example.rewear.R.id.autoComplete)
        dateAdded = findViewById<EditText>(com.example.rewear.R.id.dateAdded)
        imageView = findViewById(com.example.rewear.R.id.ivClothingItem)
        ibCamara = findViewById(com.example.rewear.R.id.ibCamera) //gets the ImageView object
        description = findViewById(com.example.rewear.R.id.description)
        checkBox = findViewById(com.example.rewear.R.id.woreToday)
        clothesData = intent.getSerializableExtra("OBJECT") as ClothesData
        screenDisplay = intent.getIntExtra("screenDisplay", 0)
        categories = intent.getBundleExtra("BUNDLE")!!
            .getSerializable("ARRAYLIST") as ArrayList<ClothesCategoryData>?
    }

    fun getCategories() {
        val newTags =
            autoCompleteView!!.text.filter { !it.isWhitespace() }.split(",").toTypedArray()
        val oldTags =
            clothesData!!.category_name!!.filter { !it.isWhitespace() }.split(",").toTypedArray()

        //get deleted categories
        if (screenDisplay == 0) {
            for (s: String in oldTags) {
                val index = newTags.indexOf(s)
                if (index == -1 && s != "ALL") {
                    val namesIndex = names!!.indexOf(s)
                    val record = ClothesBelongsToData(
                        clothesData!!.clothes_id,
                        categories!![namesIndex].category_id
                    )
                    deletedIDs.add(record)
                }
            }
        }


        //get new categories
        for (s: String in newTags) {
            val index = names!!.indexOf(s) //checks in all existing tags
            if (index != -1 && oldTags!!.indexOf(s) == -1 && s != "") {  //if tag exists for user, and  Cloth Did not have it before
                val record =
                    ClothesBelongsToData(clothesData!!.clothes_id, categories!![index].category_id)
                listOfTagsID.add(record)
            } else if (index == -1 && s != "") { //if tag does not exist for user
                val record = ClothesCategoryData(null, clothesData!!.user_id!!, s, null)
                listOfNewTags.add(record)
            }
        }

    }

    fun getNewTags(): List<ClothesCategoryData>? {
        return listOfNewTags
    }

    fun getExistingTags(): List<ClothesBelongsToData>? {
        return listOfTagsID
    }

    fun getDeleted(): List<ClothesBelongsToData> {
        return deletedIDs
    }

}