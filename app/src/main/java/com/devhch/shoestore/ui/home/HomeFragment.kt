package com.devhch.shoestore.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.devhch.shoestore.R
import com.devhch.shoestore.databinding.FragmentHomeBinding
import com.devhch.shoestore.ui.shoedetails.ShoeDetailsViewModel
import com.devhch.shoestore.utils.Utils

/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    private val shoeDetailsViewModel by activityViewModels<ShoeDetailsViewModel>()

    private lateinit var mInflater: LayoutInflater
    private lateinit var mContainer: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mInflater = inflater
        mContainer = container!!
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _navController = findNavController()
        setupMenu()

        shoeDetailsViewModel.shoes.observe(viewLifecycleOwner) { shoes ->

            Log.d("These shoes", "Shoes ${shoes.size}")
            if (!shoes.isNullOrEmpty()) {
                binding.textViewEmptyData.visibility = View.GONE
                for (item in shoes) {
                    val mView = mInflater.inflate(R.layout.item_shoe, mContainer, false)

                    val name: TextView = mView.findViewById(R.id.shoe_name_item)
                    name.text = item.name

                    val description: TextView = mView.findViewById(R.id.shoe_description_item)

                    description.text = item.description


                    val cardView: CardView = mView.findViewById(R.id.card_view_item)
                    cardView.setOnClickListener {

                    }

                    val shoeImage: ImageView = mView.findViewById(R.id.shoe_image_item)
                    shoeImage.setImageResource(item.images.first())

                    binding.linearItem.addView(mView)

                }
            } else {
                binding.textViewEmptyData.visibility = View.VISIBLE
            }
        }


        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_shoeDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                when (menuItem.itemId) {
                    R.id.menuLogOut -> logout()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun logout() {
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(Utils.IS_LOGGED_KEY, false)
        editor.apply()

        // Navigate to Login
        navController.navigate(R.id.action_nav_home_to_nav_login)
    }
}