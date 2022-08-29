package com.example.obviouscompanyassignment.nasalist.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.obviouscompanyassignment.MyApplication
import com.example.obviouscompanyassignment.R
import com.example.obviouscompanyassignment.base.di.activity.ActivityModule
import com.example.obviouscompanyassignment.base.di.activity.DaggerActivityComponent
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.nasalist.domain.NasaImageViewModel
import com.example.obviouscompanyassignment.utility.GridSpacingItemDecoration
import com.example.obviouscompanyassignment.utility.LiveDataInternetConnections
import kotlinx.android.synthetic.main.activity_nasa_grid_image.*
import javax.inject.Inject


class NasaGridActivity : AppCompatActivity() {
    @Inject
    lateinit var nasaImageViewModel: NasaImageViewModel
    private lateinit var liveDataInternetConnections: LiveDataInternetConnections
    private lateinit var nasaGridImageAdapter: NasaGridImageAdapter
    private var nasaImageResponseItemList: List<NasaImageResponseItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_grid_image)
        initViews()
    }

    private fun initViews() {
        val activityComponent = DaggerActivityComponent.builder()
            .myAppComponent((application as MyApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
        initAdapter()
        checkInternetConnection()
    }


    private fun initAdapter() {
        nasaGridImageAdapter = NasaGridImageAdapter(this)
        grid_recyclerview.apply {
            val spanCount = 2 // 3 columns
            val spacing = 50 // 50px
            val includeEdge = true
            this.addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount,
                    spacing,
                    includeEdge
                )
            )
            layoutManager = GridLayoutManager(context, 2)
            adapter = nasaGridImageAdapter
        }
    }

    private fun checkInternetConnection() {
        liveDataInternetConnections = LiveDataInternetConnections(application)
        liveDataInternetConnections.observe(this@NasaGridActivity) { isConnected ->
            if (isConnected) {
                if (nasaImageResponseItemList.isEmpty())
                    fetchAndObserveApiData()
            } else {
                Toast.makeText(
                    this@NasaGridActivity,
                    getString(R.string.internet_not_available_msg),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun fetchAndObserveApiData() {
        // fetch nasa image list
        nasaImageViewModel.fetchNasaImageListData()
        nasaImageViewModel.imageList.observe(this) {
            when (it) {
                is DataResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    dataNotAvailableTxt.text = getString(R.string.data_loading_msg)
                }
                is DataResult.Success -> {
                    progressBar.visibility = View.GONE
                    if (it.data.isNotEmpty()) {
                        dataNotAvailableTxt.visibility = View.GONE
                        nasaImageResponseItemList = it.data
                        val sortedList =
                            nasaImageResponseItemList.sortedByDescending { it.date }
                        setDataToAdapter(sortedList)
                    } else
                        dataNotAvailableTxt.text = getString(R.string.data_not_found_msg)
                }
                is DataResult.Error -> {
                    dataNotAvailableTxt.text =
                        it.message ?: getString(R.string.try_later_msg)

                }
            }

        }

    }


    private fun setDataToAdapter(sortedList: List<NasaImageResponseItem>) {
        grid_recyclerview.visibility = View.VISIBLE
        nasaGridImageAdapter.update(sortedList)

    }
}