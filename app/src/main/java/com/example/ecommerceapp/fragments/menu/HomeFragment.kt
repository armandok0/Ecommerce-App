package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.HomeViewPageAdapter
import com.example.ecommerceapp.fragments.categories.BaseBedsFragment
import com.example.ecommerceapp.fragments.categories.BaseChairsFragment
import com.example.ecommerceapp.fragments.categories.BaseDecorFragment
import com.example.ecommerceapp.fragments.categories.BaseSofasFragment
import com.example.ecommerceapp.fragments.categories.BaseTablesFragment
import com.example.ecommerceapp.fragments.categories.MainCategoryFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            BaseSofasFragment(),
            BaseTablesFragment(),
            BaseChairsFragment(),
            BaseBedsFragment(),
            BaseDecorFragment()
        )

        // Find views
        val viewPagerHome: ViewPager2 = view.findViewById(R.id.viewpagerHome)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val searchBar: ConstraintLayout = view.findViewById(R.id.searchBar)
        val searchText: TextView = view.findViewById(R.id.searchText)
        val searchIcon: ImageView = view.findViewById(R.id.searchIcon)

        viewPagerHome.isUserInputEnabled = false

        val viewPage2Adapter = HomeViewPageAdapter(childFragmentManager, lifecycle, categoriesFragments)
        viewPagerHome.adapter = viewPage2Adapter

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

        searchBar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        searchText.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        searchIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}
