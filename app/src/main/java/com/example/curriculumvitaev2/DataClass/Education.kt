package com.example.curriculumvitaev2.DataClass

import java.util.*

data class Education(
    val id:Int = getAutoId(),
    val name: String,
    val adress: String,
    val endDate: String,
    val startDate:String,
    val img: Int) {
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(999999999)
        }
    }
}