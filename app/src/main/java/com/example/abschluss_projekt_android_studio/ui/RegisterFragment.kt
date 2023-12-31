package com.example.abschluss_projekt_android_studio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschluss_projekt_android_studio.FirebaseViewModel
import com.example.abschluss_projekt_android_studio.R
import com.example.abschluss_projekt_android_studio.databinding.FragmentRegisterBinding

class RegisterFragment:Fragment() {


    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }


        binding.btRegister.setOnClickListener {
            val email = binding.tietEmailRegister.text.toString()
            val password = binding.tietPasswordRegister.text.toString()

            if (email != "" && password != "") {
                viewModel.register(email, password)
            }
        }


        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.loginFragment)
            }
        }



    }



}