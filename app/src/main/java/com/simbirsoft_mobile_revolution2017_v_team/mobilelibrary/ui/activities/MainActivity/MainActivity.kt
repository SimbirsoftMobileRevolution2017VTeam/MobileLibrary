package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities.MainActivity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.FavouriteBooksFragmentK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.LibraryFragmentK

/**
 * Created by user on 15.11.2017.
 */

class MainActivityK : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tablayout) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapterK(supportFragmentManager)
        adapter.addFragment(LibraryFragmentK(), resources.getString(R.string.all_books))
        adapter.addFragment(FavouriteBooksFragmentK(), resources.getString(R.string.favourites))
        viewPager!!.adapter = adapter
    }
}
