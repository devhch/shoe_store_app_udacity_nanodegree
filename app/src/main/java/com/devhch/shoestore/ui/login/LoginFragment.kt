package com.devhch.shoestore.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.devhch.shoestore.R
import com.devhch.shoestore.databinding.FragmentLoginBinding
import com.devhch.shoestore.utils.Utils

/*
* Created By Mirai Devs.
* On 15/10/2022.
*/

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _navController = findNavController()

        binding.loginButton.setOnClickListener { login() }
        binding.loggingWithAnExistingAccount.setOnClickListener { loginWithAnExistingAccount() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login() {
        val email: String = binding.emailEditText.text.toString()
        val password: String = binding.passwordEditText.text.toString()

        if (!isEmailValid(email)) {
            Toast.makeText(requireContext(), R.string.invalid_username, Toast.LENGTH_SHORT).show()
        } else if (!isPasswordValid(password)) {
            Toast.makeText(requireContext(), R.string.invalid_password, Toast.LENGTH_SHORT).show()
        } else {
            saveLoginData(email, password)

            // Navigate To Home Fragment
            navController.navigate(R.id.action_nav_login_to_nav_home)
        }
    }

    private fun loginWithAnExistingAccount() {
        // Save is Logged
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(Utils.IS_LOGGED_KEY, true)
        editor.apply()

        // Navigate To Home Fragment
        navController.navigate(R.id.action_nav_login_to_nav_home)
    }

    private fun saveLoginData(email: String, password: String) {
        val sharedPreferences: SharedPreferences =
            requireContext()
                .getSharedPreferences(Utils.PREF_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(Utils.IS_LOGGED_KEY, true)
        editor.putString(Utils.EMAIL_KEY, email)
        editor.putString(Utils.PASSWORD_KEY, password)
        editor.apply()
    }

    // A placeholder username validation check
    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
           false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}