package com.example.abschluss_projekt_android_studio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.abschluss_projekt_android_studio.FirebaseViewModel
import com.example.abschluss_projekt_android_studio.databinding.FragmentNotesBinding
import com.example.abschluss_projekt_android_studio.data.model.Note
import com.syntax_institut.android_vl_firebase.adapter.NotesAdapter

class NotesFragment: Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val viewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.notesRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myNotesList = value.map { it.toObject(Note::class.java) }
                binding.rvNotes.adapter = NotesAdapter(myNotesList, viewModel)
            }
        }


        binding.btSaveNote.setOnClickListener {
            val text = binding.tietNotes.text.toString()
            if (text != "") {
                viewModel.saveNote(Note(text = text))
            }
        }

    }

}