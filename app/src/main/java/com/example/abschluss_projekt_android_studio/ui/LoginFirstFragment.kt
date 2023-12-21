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
import com.example.abschluss_projekt_android_studio.databinding.FragmentLoginFirstBinding


class LoginFirstFragment : Fragment() {

    private lateinit var binding: FragmentLoginFirstBinding
    private val viewModel: FirebaseViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btToRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }


        binding.btSendPasswordReset.setOnClickListener {
            findNavController().navigate(R.id.passwordResetFragment2)
        }


        binding.btLogin.setOnClickListener {
            val email = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()

            if (email != "" && password != "") {
                viewModel.login(email, password)
            }
        }

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment2)
            }
        }

    }


}
