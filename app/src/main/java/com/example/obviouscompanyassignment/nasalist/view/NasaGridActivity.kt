package com.example.obviouscompanyassignment.nasalist.view

import android.content.Context
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_nasa_grid_image.*
import javax.inject.Inject


class NasaGridActivity : AppCompatActivity() {
    @Inject
    lateinit var nasaImageViewModel: NasaImageViewModel
    private lateinit var nasaGridImageAdapter: NasaGridImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_grid_image)
        initViews(this)
    }

    private fun initViews(context: Context) {
        val activityComponent = DaggerActivityComponent.builder()
            .myAppComponent((application as MyApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)

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
        fetchAndObserveApiData()
    }

    private fun fetchAndObserveApiData() {
        // fetch nasa image list
        nasaImageViewModel.fetchNasaImageListData()
        nasaImageViewModel.nasaImageListData().observe(this) {
            when (it) {
                is DataResult.Loading -> {}
                is DataResult.Success -> {
                    val nasaImageResponseItemList = it.data
                    val sortedList = nasaImageResponseItemList.sortedByDescending { it.date }
                    nasaGridImageAdapter.update(sortedList)
                }
                is DataResult.Error -> {}
            }

        }

    }
}