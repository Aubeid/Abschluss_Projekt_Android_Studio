package com.example.abschluss_projekt_android_studio.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.abschluss_projekt_android_studio.FirebaseViewModel
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.abschluss_projekt_android_studio.R
import com.example.abschluss_projekt_android_studio.databinding.FragmentLoginBinding
import com.example.abschluss_projekt_android_studio.data.model.Profile


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: FirebaseViewModel by activityViewModels()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.uploadImage(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivProfilePic.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btToNotes.setOnClickListener {
            findNavController().navigate(R.id.notesFragment2)
        }


        var image = ""

        viewModel.profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myProfile = value.toObject(Profile::class.java)
                binding.tietFirstName.setText(myProfile!!.firstName)
                binding.tietLastName.setText(myProfile.lastName)
                if (myProfile.profilePicture != "") {
                    binding.ivProfilePic.load(myProfile.profilePicture)
                    image = myProfile.profilePicture
                }
            }


            binding.btLogout.setOnClickListener {
                viewModel.logout()
            }


            viewModel.currentUser.observe(viewLifecycleOwner) {
                if (it == null) {
                    findNavController().navigate(R.id.loginFragment)
                }
            }


            binding.btSave.setOnClickListener {
                val firstName = binding.tietFirstName.text.toString()
                val lastName = binding.tietLastName.text.toString()

                if (firstName != "" && lastName != "") {
                    val newProfile = Profile(firstName, lastName, image)
                    viewModel.updateProfile(newProfile)
                }

            }


        }


    }
}