package com.example.ageindays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ageindays.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {

        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_WEEK)

        var dialog=DatePickerDialog(this, { datePicker, year, month, dayInMonth ->
            val selectDate="$dayInMonth/${month + 1}/$year"
            binding.btnDatePicker.text=selectDate

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            var theDate=sdf.parse(selectDate)
            theDate?.let {
                var selectedmin=theDate.time / 3600000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentMin=currentDate.time / 3600000
                    val diffInMin=currentMin - selectedmin
                    binding.tvSelectedDateInMinutes.text=diffInMin.toString()
                }
            }

        }, year, month, day)
        dialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dialog.show()
    }
}