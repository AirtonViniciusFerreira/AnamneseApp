package com.example.anamnesedrapp.util.navegacao

import androidx.navigation.NavController

fun NavController.tutorNavigateToTutorEdit(tutorId: String) {
    if (tutorId.isNotBlank())
        this.navigate("tutor/edit?tutorId=${tutorId}")
}

fun NavController.tutorNavigateToTutorEdit() {
    this.navigate("tutor/edit")
}