package com.example.obviouscompanyassignment.nasaimagedetail.view

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.obviouscompanyassignment.R
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.*

class NasaImageViewPagerAdapter(val context: Context, val imageList: List<NasaImageResponseItem>) :
    PagerAdapter() {
    private val TAG = "NasaImageViewPagerAdapt"

    // on below line we are creating a method
    // as get count to return the size of the list.
    override fun getCount(): Int {
        return imageList.size
    }

    // on below line we are returning the object
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    // on below line we are initializing
    // our item and inflating our layout file
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // on below line we are initializing
        // our layout inflater.
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // on below line we are inflating our custom
        // layout file which we have created.
        val itemView: View =
            mLayoutInflater.inflate(R.layout.nasa_pager_image_raw, container, false)

        val imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView
        val progressBar: ProgressBar = itemView.findViewById<View>(R.id.progressBar) as ProgressBar
        val imageClickDateValue=itemView.findViewById<View>(R.id.imageClickDateValue) as TextView
        val imageTitleValue=itemView.findViewById<View>(R.id.imageTitleValue) as TextView
        val imageCopyRightValue=itemView.findViewById<View>(R.id.imageCopyRightValue) as TextView
        val imageDescValue=itemView.findViewById<View>(R.id.imageDescValue) as TextView
        val nasaImageResponseItem = imageList[position]
        imageClickDateValue.text=nasaImageResponseItem.date
        imageTitleValue.text=nasaImageResponseItem.title
        imageCopyRightValue.text=nasaImageResponseItem.copyright
        imageDescValue.text=nasaImageResponseItem.explanation
        setImage(imageView, progressBar, nasaImageResponseItem)
        Objects.requireNonNull(container).addView(itemView)

        // on below line we are simply
        // returning our item view.
        return itemView
    }

    private fun setImage(
        imageView: ImageView,
        progressBar: ProgressBar,
        nasaImageResponseItem: NasaImageResponseItem
    ) {
        val builder = Picasso.Builder(context);
        builder.listener(object : Picasso.Listener {
            override fun onImageLoadFailed(picasso: Picasso?, uri: Uri?, exception: Exception?) {
                Log.i(TAG, "onImageLoadFailed: " + exception!!.message)
            }
        })
        builder.build().load(nasaImageResponseItem.url)
            .placeholder(android.R.drawable.ic_delete)
            .error(android.R.drawable.ic_btn_speak_now)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError() {
                    progressBar.visibility = View.VISIBLE
                }
            })

    }


    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as ConstraintLayout)
    }
}
