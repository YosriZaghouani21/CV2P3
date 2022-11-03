package com.example.curriculumvitaev2.DataClass

import android.view.autofill.AutofillId
import java.util.*

data class Experience(
    val id:Int = getAutoId(),
    val name: String,
    val adress: String,
    val endDate: String,
    val description: String,
    val img: Int,
    val startDate:String) {
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(999999999)
        }
    }
}