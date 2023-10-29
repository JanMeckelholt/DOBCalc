package de.janmeckelholt.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvMinutesSince: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvMinutesSince = findViewById(R.id.tvMinutesSince)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }

   private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val yearNow = myCalender.get(Calendar.YEAR)
        val monthNow = myCalender.get(Calendar.MONTH)
        val dayNow = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDateStr = "$dayOfMonth.${month + 1}.$year"
                tvSelectedDate?.text = selectedDateStr
                val sdf = SimpleDateFormat("dd.MM.yyy", Locale.GERMANY)
                val selectedDate = sdf.parse(selectedDateStr)
                selectedDate?.let {
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val elapsedMinutes = currentDate.time / 60000 - selectedDate.time / 60000
                        tvMinutesSince?.text = elapsedMinutes.toString()
                    }

                }

            },
            yearNow,
            monthNow,
            dayNow
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 24 * 60 * 1000
        dpd.show()
    }

}