package android.example.age_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button = findViewById(R.id.btnDateClicker)
        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                // selectedDate - string odabranog datuma, viewSelectedDate - povlacenje ID-a
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val presentDate = "$day/${month+1}/$year"
                val viewSelectedDate: TextView= findViewById(R.id.tvSelectedDate)

                // odabrani datum prikazan u textboxu
                viewSelectedDate.text = selectedDate

                // simple date format postavljanje
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val presentDateParse = sdf.parse(presentDate)
                val theDate = sdf.parse(selectedDate)
                val date1 = theDate.time
                val date2 = presentDateParse.time


                val diff: Long = date2 - date1
                val seconds: Long = diff / 1000
                val minutes: Long = seconds / 60

                val viewSelectedDateInMinutes: TextView= findViewById(R.id.tvSelectedDateInMinutes)

                viewSelectedDateInMinutes.text = "$minutes"

            }
                , year
                , month
                , day).show()
    }
}