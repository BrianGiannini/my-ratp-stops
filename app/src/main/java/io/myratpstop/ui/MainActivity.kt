package io.myratpstop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import io.myratpstop.R
import io.myratpstop.system.Constants.currentStop
import io.myratpstop.system.Constants.currentWay
import io.myratpstop.system.SectionsPagerAdapter
import io.myratpstop.system.Tools
import io.myratpstop.system.Tools.disposable


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
    }

    override fun onResume() {
        super.onResume()

        Tools.beginSearch(currentStop, currentWay)
    }

    override fun onPause() {
        super.onPause()

        disposable?.dispose()
    }







}
