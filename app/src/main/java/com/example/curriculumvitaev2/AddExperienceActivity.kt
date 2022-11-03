package com.example.curriculumvitaev2

import DataBase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.curriculumvitaev2.DataClass.Experience
import com.google.android.material.textfield.TextInputLayout

class AddExperienceActivity : AppCompatActivity() {


    private lateinit var nameE :TextInputLayout
    private lateinit var addressE :TextInputLayout
    private lateinit var descriptioE:TextInputLayout
    private lateinit var startDateE:TextInputLayout
    private lateinit var endDateE :TextInputLayout
    private lateinit var imageUp :ImageView
    private lateinit var submit : Button
    private lateinit var sqLiteHelper: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_experience)

        initViews()
        sqLiteHelper = DataBase(context = this)

        submit.setOnClickListener {
            if (verifyIsEmpty()){
                addExperience()
            }else{
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
            }

        }

        imageUp.setOnClickListener{
            getExperiences()
        }
    }

    private fun getExperiences() {
        val exprList = sqLiteHelper.getAllExperiences()
        Log.i("Size",exprList.toString())
    }

    private fun addExperience() {
        val name = nameE.editText?.text.toString()
        val address = addressE.editText?.text.toString()
        val description = descriptioE.editText?.text.toString()
        val startDate = startDateE.editText?.text.toString()
        val endDate = endDateE.editText?.text.toString()
        val image = (R.drawable.ic_logo_microsoft)

        val expr = Experience( name = name, adress = address, description = description, startDate = startDate, endDate = endDate, img = image )
        val status = sqLiteHelper.insertExperience(expr)
        if (status > -1) {
            Toast.makeText(
                this, "experince successfully added",
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
        nameE = findViewById(R.id.company_name)
        addressE = findViewById(R.id.company_address)
        descriptioE = findViewById(R.id.company_description)
        startDateE = findViewById(R.id.start_date)
        endDateE = findViewById(R.id.end_date)
        imageUp = findViewById(R.id.imageView3)
        submit = findViewById(R.id.submit)
    }
    private fun verifyIsEmpty() : Boolean {
        return !(nameE.editText?.text.toString().equals("") == true || addressE.editText?.text.toString().equals("") == true || descriptioE.editText?.text.toString().equals("") == true || startDateE.editText?.text.toString().equals("") == true ||endDateE.editText?.text.toString().equals("") == true)

    }
}


