package com.kotlin.balancehero.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.balancehero.databinding.ActivityMainBinding
import com.kotlin.balancehero.ui.adapter.SectionsPagerAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.addFragment(Tab1Fragment(), "Tab1")
        sectionsPagerAdapter.addFragment(Tab2Fragment(), "Tab2")

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = "TAB " + (position + 1)
        }.attach()
    }
}