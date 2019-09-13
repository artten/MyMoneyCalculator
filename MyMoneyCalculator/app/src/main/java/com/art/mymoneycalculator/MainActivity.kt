package com.art.mymoneycalculator

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plus50 = findViewById<Button>(R.id.plus50)
        val plus100 = findViewById<Button>(R.id.plus100)
        val plus200 = findViewById<Button>(R.id.plus200)
        val minus50 = findViewById<Button>(R.id.minus50)
        val minus100 = findViewById<Button>(R.id.minus100)
        val minus200 = findViewById<Button>(R.id.minus200)
        val total = findViewById<TextView>(R.id.total)
        val salary = findViewById<EditText>(R.id.salary)
        val change = findViewById<EditText>(R.id.change)
        val add = findViewById<Button>(R.id.add)

        try {
            val sharedPreferences = getSharedPreferences("allData", Context.MODE_PRIVATE)
            salary.setText( sharedPreferences.getString("salary","0"))
            var year = Calendar.getInstance().get(Calendar.YEAR)
            var month = Calendar.getInstance().get(Calendar.MONTH)
            var TotalMoney = sharedPreferences.getString("total","0").toInt() + salary.text.toString().toInt()*(
                            year - sharedPreferences.getString("year", Calendar.getInstance().get(Calendar.YEAR).toString()).toInt()
                            + month - sharedPreferences.getString("month", Calendar.getInstance().get(Calendar.MONTH).toString()).toInt())
            total.text = TotalMoney.toString()
        }
        catch (e: IOException)
        {
            salary.setText("0")
            total.text = "0"
        }

        plus50.setOnClickListener(View.OnClickListener {
            var newTotal = total.text.toString()
            var totalMoney = newTotal.toInt()
            totalMoney = totalMoney + 50
            newTotal = totalMoney.toString()
            total.text = newTotal

        })

        plus100.setOnClickListener(View.OnClickListener {
            var newTotal = total.text.toString()
            var totalMoney = newTotal.toInt()
            totalMoney = totalMoney + 100
            newTotal = totalMoney.toString()
            total.text = newTotal

        })

        plus200.setOnClickListener(View.OnClickListener {
            var newTotal = total.text.toString()
            var totalMoney = newTotal.toInt()
            totalMoney = totalMoney + 200
            newTotal = totalMoney.toString()
            total.text = newTotal

        })

        minus50.setOnClickListener(View.OnClickListener {
            var newTotal = total.text.toString()
            var totalMoney = newTotal.toInt()
            totalMoney = totalMoney - 50
            newTotal = totalMoney.toString()
            total.text = newTotal

        })

        minus100.setOnClickListener(View.OnClickListener {
            var totalMoney = total.text.toString().toInt()
            totalMoney = totalMoney - 100
            var newTotal = totalMoney.toString()
            total.text = newTotal

        })

        minus200.setOnClickListener(View.OnClickListener {
            var newTotal = total.text.toString()
            var totalMoney = newTotal.toInt()
            totalMoney = totalMoney - 200
            newTotal = totalMoney.toString()
            total.text = newTotal

        })


        add.setOnClickListener(View.OnClickListener {
            try {
                var toChange = change.text.toString().toInt()
                total.text = toChange.toString()
                change.setTextColor(Color.BLACK)
            } catch (e: IOException) {
                change.setTextColor(Color.RED)
            }

        })

        change.setOnFocusChangeListener { _, b -> if(b) change.setText("") }

        salary.setOnFocusChangeListener { _, b -> if(b) salary.setText("") }

    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("allData", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("salary", salary.text.toString())
        editor.putString("total", total.text.toString())
        editor.putString("year", Calendar.getInstance().get(Calendar.YEAR).toString())
        editor.putString("month", Calendar.getInstance().get(Calendar.MONTH).toString())
        editor.commit()
    }

}
