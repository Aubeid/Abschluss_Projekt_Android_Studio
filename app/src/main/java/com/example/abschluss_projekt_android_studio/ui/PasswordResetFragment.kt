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
import com.example.abschluss_projekt_android_studio.databinding.FragmentPasswordForgottenBinding

class PasswordResetFragment: Fragment() {

    private lateinit var binding: FragmentPasswordForgottenBinding
    private val viewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordForgottenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button um Passwort-Vergessen Mail zu senden
        // Email wird aus den Input-Feldern geholt
        // Wenn Email kein leerer String ist wird die sendPasswordResetFunktion im ViewModel aufgerufen
        binding.btSendEmailPasswordReset.setOnClickListener {
            val email = binding.tietEmailPasswordReset.text.toString()

            if (email != "") {
                viewModel.sendPasswordReset(email)
            }

        }

        // Button um zurück zum LoginFragment zu navigieren
        binding.btBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

    }
}