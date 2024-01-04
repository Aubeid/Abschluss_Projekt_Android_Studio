package com.example.abschluss_projekt_android_studio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschluss_projekt_android_studio.R
import com.example.abschluss_projekt_android_studio.data.model.HauptItems
import com.example.abschluss_projekt_android_studio.databinding.ItemHomeBinding

class HomeAdapter(
    private val dataset:List<HauptItems>
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding:ItemHomeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return dataset.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.ivImage.setImageResource(item.image)
        holder.binding.tvHauptName.text = item.name

        holder.binding


        holder.binding.cvHome.setOnClickListener {
            when(item.name){
                "Private Notizen" -> {
                    holder.itemView.findNavController().navigate(R.id.notesFragment2)
                }
                "Gemeinsame Notizen"->{
                    holder.itemView.findNavController().navigate(R.id.notesFragment2)
                }
            }


        }
    }
}