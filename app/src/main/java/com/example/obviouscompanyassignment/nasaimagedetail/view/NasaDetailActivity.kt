package com.example.obviouscompanyassignment.nasaimagedetail.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.obviouscompanyassignment.MyApplication
import com.example.obviouscompanyassignment.R
import com.example.obviouscompanyassignment.base.di.activity.ActivityModule
import com.example.obviouscompanyassignment.base.di.activity.DaggerActivityComponent
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.utility.Constants
import kotlinx.android.synthetic.main.activity_detail.*


class NasaDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        val sortedList =
            intent.getSerializableExtra(Constants.IMAGE_LIST) as ArrayList<NasaImageResponseItem>
        val clickImagePosition = intent.getIntExtra(Constants.IMAGE_CLICK_POSITION, 0)

        val activityComponent = DaggerActivityComponent.builder()
            .myAppComponent((application as MyApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
        val viewPagerAdapter = NasaImageViewPagerAdapter(this, sortedList)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = clickImagePosition
        dotsIndicator.attachTo(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
