package com.app.movie.ui.main.mainactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.movie.R
import com.app.movie.ui.main.mainactivity.fragment.MovieFragment
import com.app.movie.ui.main.mainactivity.fragment.TvShowsFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val movieFragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        tablayout.addTab(tablayout.newTab().setText("Movies"))
        tablayout.addTab(tablayout.newTab().setText("TV Shows"))
        tablayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(supportFragmentManager, tablayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))

        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setupObserver() {
        with(mainViewModel) {
            getMovieList("1")
            filmDetail.observe(this@MainActivity, {
                movieFragment.setDetail(it)
            })
            trendingFilm.observe(this@MainActivity, {
                movieFragment.setTrending(it)
            })
            discoveryFilm.observe(this@MainActivity, {
                movieFragment.setDiscover(it)
            })
        }
    }

    inner class MyAdapter(
        fm: FragmentManager,
        private var totalTabs: Int
    ) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = totalTabs

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> movieFragment
                else -> TvShowsFragment()
            }
        }


    }
}