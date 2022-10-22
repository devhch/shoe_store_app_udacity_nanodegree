package com.devhch.shoestore.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devhch.shoestore.databinding.OnboardingSliderBinding
import com.devhch.shoestore.models.OnBoarding

/*
* Created By Mirai Devs.
* On 17/10/2022.
*/

class OnBoardingPagerAdapter(
    private var onBoardingDataList: List<OnBoarding>,

    ) : RecyclerView.Adapter<OnBoardingPagerAdapter.OnBoardingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: OnboardingSliderBinding = OnboardingSliderBinding.inflate(
            inflater, parent, false
        )
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val onBoarding: OnBoarding = onBoardingDataList[position]
        holder.bind(onBoarding)
    }

    override fun getItemCount(): Int {
        return onBoardingDataList.size
    }

    class OnBoardingViewHolder(
        private val binding: OnboardingSliderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.onBoarding = onBoarding
        }
    }

}
