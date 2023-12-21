package com.example.abschluss_projekt_android_studio.data.model

import com.google.firebase.firestore.DocumentId


data class Note(
    // Mit @DocumentId bekommen wir die Id des Firebase-Documents
    @DocumentId
    val id: String = "",
    val text: String = ""
)
