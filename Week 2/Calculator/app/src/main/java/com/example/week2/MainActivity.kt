package com.example.week2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var editTextNumber1: EditText
    lateinit var editTextNumber2: EditText
    lateinit var btnAdd: Button
    lateinit var btnSubtract: Button
    lateinit var btnMultiply: Button
    lateinit var btnDivide: Button
    lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        textViewResult = findViewById(R.id.textViewResult)

        // Set click listeners
        btnAdd.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivide.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        val number1Str = editTextNumber1.text.toString()
        val number2Str = editTextNumber2.text.toString()

        // Check if inputs are not empty
        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            textViewResult.text = "Result: Please enter both numbers"
            return
        }

        try {
            val number1 = number1Str.toDouble()
            val number2 = number2Str.toDouble()
            var result = 0.0

            when (v?.id) {
                R.id.btnAdd -> {
                    result = number1 + number2
                }
                R.id.btnSubtract -> {
                    result = number1 - number2
                }
                R.id.btnMultiply -> {
                    result = number1 * number2
                }
                R.id.btnDivide -> {
                    if (number2 != 0.0) {
                        result = number1 / number2
                    } else {
                        textViewResult.text = "Result: Cannot divide by zero"
                        return
                    }
                }
            }

            val formattedResult = if (result == result.toInt().toDouble()) {
                result.toInt().toString()
            } else {
                String.format("%.2f", result)
            }

            textViewResult.text = "Result: $formattedResult"

        } catch (e: NumberFormatException) {
            textViewResult.text = "Result: Invalid number format"
        }
    }
}