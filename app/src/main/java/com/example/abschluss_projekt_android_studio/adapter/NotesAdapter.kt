package com.syntax_institut.android_vl_firebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschluss_projekt_android_studio.FirebaseViewModel
import com.example.abschluss_projekt_android_studio.databinding.ItemNoteBinding
import com.example.abschluss_projekt_android_studio.data.model.Note

class NotesAdapter(
    private val dataset: List<Note>,
    private val viewModel: FirebaseViewModel
): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.tvNoteText.text = item.text

        holder.binding.cvNote.setOnClickListener {
            viewModel.deleteNote(item)
        }
    }

}