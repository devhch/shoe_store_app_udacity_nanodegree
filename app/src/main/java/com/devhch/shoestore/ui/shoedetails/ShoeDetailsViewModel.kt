package com.devhch.shoestore.ui.shoedetails

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devhch.shoestore.R
import com.devhch.shoestore.models.Shoe


/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class ShoeDetailsViewModel : ViewModel() {

    var shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    fun addShoe(
        context: Context,
        shoe: Shoe,
    ) {

        // Check Data
        if (shoe.name.isEmpty()) {
            Toast.makeText(context, R.string.name_is_required, Toast.LENGTH_SHORT).show()
            return
        }

        if (shoe.company.isEmpty()) {
            Toast.makeText(context, R.string.company_is_required, Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (shoe.size.isEmpty()) {
            Toast.makeText(context, R.string.size_is_required, Toast.LENGTH_SHORT).show()
            return
        }
        if (shoe.description.isEmpty()) {
            Toast.makeText(context, R.string.description_is_required, Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Add shoe
        shoes.value?.add(shoe)


    }
}