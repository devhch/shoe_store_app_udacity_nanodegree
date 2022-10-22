package com.devhch.shoestore.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.devhch.shoestore.R
import com.devhch.shoestore.databinding.FragmentSplashBinding
import com.devhch.shoestore.utils.Utils

/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            val navController = findNavController(view)
            if (isLogged())
                navController.navigate(R.id.action_nav_splash_to_nav_home)
            else if (getOnBoardingPref())
                navController.navigate(R.id.action_nav_splash_to_nav_login)
            else
                navController.navigate(R.id.action_nav_splash_to_nav_on_boarding)
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getOnBoardingPref(): Boolean {
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(Utils.IS_FIRST_TIME_LAUNCH_KEY, false)
    }

    private fun isLogged(): Boolean {
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(Utils.IS_LOGGED_KEY, false)
    }
}