package com.example.abschluss_projekt_android_studio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abschluss_projekt_android_studio.FirebaseViewModel
import com.example.abschluss_projekt_android_studio.adapter.HomeAdapter
import com.example.abschluss_projekt_android_studio.data.Datasouce
import com.example.abschluss_projekt_android_studio.databinding.FragmentHomeBinding

class HomeFragment:Fragment(){
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: FirebaseViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        //viewModel.loadeHomeItem()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val manager : RecyclerView.LayoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.HORIZONTAL, true)
        binding.rvFotos.layoutManager = manager
         */
        binding.rvHome.adapter = HomeAdapter(Datasouce().loadHomeItem())
        binding.rvFotos.adapter = HomeAdapter(Datasouce().loadHomeItem())



        binding.buttonHinzuf.setOnClickListener{

        }




    }

}