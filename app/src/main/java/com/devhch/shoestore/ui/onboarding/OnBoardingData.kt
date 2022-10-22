package com.devhch.shoestore.ui.onboarding

import android.content.Context
import androidx.core.content.ContextCompat
import com.devhch.shoestore.R
import com.devhch.shoestore.models.OnBoarding


/*
* Created By Mirai Devs.
* On 17/10/2022.
*/

object OnBoardingData {
    fun getOnBoardingData(context: Context): List<OnBoarding> {
        val onBoardingList = arrayListOf<OnBoarding>()

    val  image0=  ContextCompat.getDrawable(context,R.drawable.white_shoe)
    val  image1=  ContextCompat.getDrawable(context,R.drawable.black_shoe)
    val  image2=  ContextCompat.getDrawable(context,R.drawable.shoe11)

        val onBoarding1 =
            OnBoarding(
                "It's just For Wearing",
                "Available in A Different Type Of moderl That Make You Happy",
                image0
            )

        val onBoarding2 =
            OnBoarding(
                "Start Journey With Nike",
                "Smart, gorgeous & fashionable collection",
                image2
            )

        val onBoarding3 =
            OnBoarding(
                "The Awesome Branded Shoes",
                "Get access to ore than 1000 nike shoes also another brands with 20% off",
                image1
            )

        onBoardingList.add(onBoarding1)
        onBoardingList.add(onBoarding2)
        onBoardingList.add(onBoarding3)

        return onBoardingList

    }
}