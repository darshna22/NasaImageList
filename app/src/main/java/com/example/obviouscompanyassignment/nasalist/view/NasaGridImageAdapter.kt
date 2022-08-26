package com.example.obviouscompanyassignment.nasalist.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.obviouscompanyassignment.R
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.nasaimagedetail.view.NasaDetailActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.Serializable

class NasaGridImageAdapter(var context: NasaGridActivity) :
    RecyclerView.Adapter<NasaGridImageAdapter.ViewHolder>() {
    var list: List<NasaImageResponseItem>? = emptyList()

    fun update(list: List<NasaImageResponseItem>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nasa_grid_image_raw, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nasaImageResponseItem = list?.get(position)
        setImage(holder, nasaImageResponseItem)
        holder.imageView.setOnClickListener {
            val intent = Intent(context, NasaDetailActivity::class.java)
            intent.putExtra("list", list as Serializable)
            intent.putExtra("position", position)
            context.startActivity(intent)
            //context.overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    private fun setImage(holder: ViewHolder, nasaImageResponseItem: NasaImageResponseItem?) {
        val builder = Picasso.Builder(context);
        builder.listener(object : Picasso.Listener {
            override fun onImageLoadFailed(picasso: Picasso?, uri: Uri?, exception: Exception?) {
                Log.i(TAG, "onImageLoadFailed: " + exception!!.message)
            }
        })
        builder.build().load(nasaImageResponseItem?.url ?: "")
            .placeholder(android.R.drawable.ic_delete)
            .error(android.R.drawable.ic_btn_speak_now)
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError() {
                    holder.progressBar.visibility = View.VISIBLE
                }
            })

    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image)
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar);
    }

    companion object {
        private const val TAG = "NasaGridImageAdapter"
    }
}