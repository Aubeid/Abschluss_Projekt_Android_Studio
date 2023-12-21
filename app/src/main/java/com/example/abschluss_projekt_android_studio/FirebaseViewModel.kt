package com.example.abschluss_projekt_android_studio


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abschluss_projekt_android_studio.data.Datasouce
import com.example.abschluss_projekt_android_studio.data.model.Note
import com.example.abschluss_projekt_android_studio.data.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseViewModel : ViewModel() {


    private val firebaseAuth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()


    private val storege = FirebaseStorage.getInstance()

    private val storegeRef = storege.reference


    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser


    lateinit var profileRef: DocumentReference

    val notesRef = fireStore.collection("notes")


    init {
        if (firebaseAuth.currentUser != null) {
            profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
        }
    }


    fun uploadImage(uri: Uri) {
        val imageRef = storegeRef.child("images/${firebaseAuth.currentUser!!.uid}/profilePic")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    setUserImage(it.result)
                }
            }
        }
    }


    private fun setUserImage(uri: Uri) {
        profileRef.update("profilePicture", uri.toString())
    }

    fun saveNote(note: Note){
        notesRef.add(note)
    }

    fun deleteNote(note: Note){
        notesRef.document(note.id).delete()
    }


    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()
                    profileRef =
                        fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
                    profileRef.set(Profile())
                    logout()

                } else {
                    Log.e("Fehler Firebase", "${authResult.exception}")
                }
            }


    }


    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    if (firebaseAuth.currentUser!!.isEmailVerified) {
                        profileRef = fireStore.collection("profiles")
                            .document(firebaseAuth.currentUser!!.uid)

                        _currentUser.value = firebaseAuth.currentUser

                    } else {
                        Log.e("FIREBASE", "User not verified")
                        logout()
                    }
                } else {
                    Log.e("FIREBASE", "${authResult.exception}")
                }
            }


    }


    fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }


    fun logout() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser

    }


    fun updateProfile(profile: Profile) {
        profileRef.set(profile)

    }

}