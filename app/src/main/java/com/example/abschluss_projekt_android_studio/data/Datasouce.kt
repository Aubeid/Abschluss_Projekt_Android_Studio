package com.example.abschluss_projekt_android_studio.data

import com.example.abschluss_projekt_android_studio.R
import com.example.abschluss_projekt_android_studio.data.model.HauptItems

class Datasouce {

    fun loadHomeItem():List<HauptItems>{
        return listOf(
            HauptItems("Private Notizen", R.drawable.notiz),
            HauptItems("Gemeinsame Notizen",R.drawable.notiz),
            HauptItems("Famielen Chat",R.drawable.sprechblase),
            HauptItems("Was gibt es zu Essen",R.drawable.food_blogger),
            HauptItems("Stundenplan",R.drawable.kalender)
        )
    }



}