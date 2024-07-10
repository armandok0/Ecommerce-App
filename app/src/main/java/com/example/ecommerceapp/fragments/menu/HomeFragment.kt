package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.HomeViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.fragments.categories.BaseBedsFragment
import com.example.ecommerceapp.fragments.categories.BaseChairsFragment
import com.example.ecommerceapp.fragments.categories.BaseDecorFragment
import com.example.ecommerceapp.fragments.categories.BaseSofasFragment
import com.example.ecommerceapp.fragments.categories.BaseTablesFragment
import com.example.ecommerceapp.fragments.categories.MainCategoryFragment


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // List of fragments to be displayed in the ViewPager
        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            BaseSofasFragment(),
            BaseTablesFragment(),
            BaseChairsFragment(),
            BaseBedsFragment(),
            BaseDecorFragment()
        )

        // Find
        val viewPagerHome: ViewPager2 = view.findViewById(R.id.viewpagerHome)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)

        // Disable user input to prevent manual swiping
        viewPagerHome.isUserInputEnabled = false

        // Create an adapter for the ViewPager2 with the fragment list
        val viewPage2Adapter = HomeViewPageAdapter(childFragmentManager, lifecycle, categoriesFragments)
        viewPagerHome.adapter = viewPage2Adapter

        // Link
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_home)
                1 -> tab.text = getString(R.string.tab_sofas)
                2 -> tab.text = getString(R.string.tab_tables)
                3 -> tab.text = getString(R.string.tab_chairs)
                4 -> tab.text = getString(R.string.tab_beds)
                5 -> tab.text = getString(R.string.tab_decor)
                else -> tab.text = null
            }
        }
        tabLayoutMediator.attach()
    }
}
