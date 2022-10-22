package com.devhch.shoestore.ui.onboarding

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.devhch.shoestore.R
import com.devhch.shoestore.databinding.FragmentOnboardingBinding
import com.devhch.shoestore.utils.Utils

/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private var onBoardingPagerAdapter: OnBoardingPagerAdapter? = null
    var onBoardingViewPager2: ViewPager2? = null

    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBoardingPagerAdapter(view)
    }

    private fun setOnBoardingPagerAdapter(view: View) {
        onBoardingViewPager2 = binding.viewPager

        val onBoardingList = OnBoardingData.getOnBoardingData(requireContext())
        onBoardingPagerAdapter = OnBoardingPagerAdapter(onBoardingList)
        onBoardingViewPager2!!.adapter = onBoardingPagerAdapter

        position = onBoardingViewPager2!!.currentItem

        binding.next.setOnClickListener {
            if (position < onBoardingList.size) {
                position++
                onBoardingViewPager2!!.currentItem = position
            }
            if (position == onBoardingList.size) {
                savePnBoardingPrefData()
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.action_nav_on_boarding_to_nav_login)
            }
        }

        TabLayoutMediator(binding.tabIndicator, binding.viewPager
        ) { tab, _ ->
            if (tab.position == onBoardingList.size - 1) {
                binding.next.text = getString(R.string.get_started)
            } else {
                binding.next.text = getString(R.string.next)
            }
        }.attach()

        binding.tabIndicator.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
                if (tab.position == onBoardingList.size - 1) {
                    binding.next.text = getString(R.string.get_started)
                } else {
                    binding.next.text = getString(R.string.next)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun savePnBoardingPrefData() {
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(Utils.IS_FIRST_TIME_LAUNCH_KEY, true)
        editor.apply()
    }
}