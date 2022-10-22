package com.devhch.shoestore.ui.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.devhch.shoestore.R
import com.devhch.shoestore.databinding.FragmentShoeDetailsBinding
import com.devhch.shoestore.models.Shoe
import kotlin.random.Random

/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class ShoeDetailsFragment : Fragment() {

    private var _binding: FragmentShoeDetailsBinding? = null
    private val binding get() = _binding!!

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    private val shoeDetailsViewModel by activityViewModels<ShoeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _navController = findNavController()

        binding.cancelButton.setOnClickListener {
            navController.navigateUp()
        }

        binding.addShoeButton.setOnClickListener {
            // Find Views
            val name: String = binding.nameEditText.text.toString()
            val size: String = binding.sizeEditText.text.toString()
            val company: String = binding.companyEditText.text.toString()
            val description: String = binding.descriptionEditText.text.toString()

            // Create a shoe...
            val drawable: Int = randomDrawable()
            val shoe = Shoe(name, size, company, description, images = listOf(drawable))
            binding.shoe = shoe

            // Add Shoe
            shoeDetailsViewModel.addShoe(requireContext(), shoe)

            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun randomDrawable(): Int {
        val list = listOf(
            R.drawable.black_shoe,
            R.drawable.blue_shoe,
            R.drawable.shoe11,
            R.drawable.white_shoe,
            R.drawable.splash_shoe
        )
        val randomIndex = Random.nextInt(list.size);

        return list[randomIndex]
    }

}