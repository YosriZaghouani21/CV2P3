package com.example.curriculumvitaev2

import DataBase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.curriculumvitaev2.DataClass.Education
import com.google.android.material.textfield.TextInputLayout

class AddEducationActivity : AppCompatActivity() {
    private lateinit var nameU :TextInputLayout
    private lateinit var addressU :TextInputLayout
    private lateinit var startDateU:TextInputLayout
    private lateinit var endDateU :TextInputLayout
    private lateinit var imageUp : ImageView
    private lateinit var submit : Button
    private lateinit var sqLiteHelper: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_education)

        initViews()
        sqLiteHelper = DataBase(context = this)

        submit.setOnClickListener {
            if (verifyIsEmpty()){
                addEducations()
            }else{
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
            }

        }

        imageUp.setOnClickListener{
            getEducations()
        }
    }

    private fun getEducations() {
        val educList = sqLiteHelper.getAllEducations()
        Log.i("Size",educList.toString())
    }

    private fun addEducations() {
        val name = nameU.editText?.text.toString()
        val address = addressU.editText?.text.toString()
        val startDate = startDateU.editText?.text.toString()
        val endDate = endDateU.editText?.text.toString()
        val image = (R.drawable.ic_logo_cambridge)

        val educ = Education( name = name, adress = address, startDate = startDate, endDate = endDate, img = image )
        val status = sqLiteHelper.insertEducation(educ)
        if (status > -1) {
            Toast.makeText(
                this, "education successfully added",
                Toast.LENGTH_SHORT
            ).show()
            //TODO(clear edit text)
            //clearEditText()
        } else {
            Toast.makeText(
                this, "Please try again",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
    private fun initViews() {
        nameU = findViewById(R.id.university_name)
        addressU = findViewById(R.id.univerity_address)
        startDateU = findViewById(R.id.start_date)
        endDateU = findViewById(R.id.end_date)
        imageUp = findViewById(R.id.imageView3)
        submit = findViewById(R.id.submit)
    }
    private fun verifyIsEmpty() : Boolean {
        return !(nameU.editText?.text.toString().equals("") == true || addressU.editText?.text.toString().equals("") == true ||  startDateU.editText?.text.toString().equals("") == true ||endDateU.editText?.text.toString().equals("") == true)

    }
    }
