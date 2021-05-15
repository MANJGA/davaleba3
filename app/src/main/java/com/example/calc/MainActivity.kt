package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var button: Button
    private var operand: Double = 0.0
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.resultTextView = findViewById(R.id.resultTextView)
        button = findViewById(R.id.delId)

        button.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                resultTextView.text = ""
                return true  }
        })
    }

    fun numberClick(clickedview: View) {

        if (clickedview is Button) {


            val number: String = clickedview.text.toString()
            var result: String = this.resultTextView.text.toString()

            if (result == "0") {
                result = ""
            }


            resultTextView.text = "$result$number"

        }

    }

    fun operationClick(view: View) {

        if (view is Button) {

            if (resultTextView.text.isNotEmpty()) {
                this.operand = resultTextView.text.toString().toDouble()
            }

            this.operation = view.text.toString()


            this.resultTextView.text = ""
        }
    }

    fun equalsClick(view: View) {


        val secOperandText: String = this.resultTextView.text.toString()
        var secOperand: Double = 0.0

        if (secOperandText.isNotEmpty()) {
            secOperand = secOperandText.toDouble()
        }

        when(this.operation) {

            "+" -> this.resultTextView.text = (this.operand + secOperand).toString()
            "-" -> this.resultTextView.text = (this.operand - secOperand).toString()
            "*" -> this.resultTextView.text = (this.operand * secOperand).toString()
            "/" -> this.resultTextView.text = (this.operand / secOperand).toString()

        }

        var saved = resultTextView.text.toString()
        var check = 1
        for (i in 0..saved.length) {
            if (saved[i] == '.') {
                var b = saved.substring(i + 1)

                for (k in b) {
                    if (k != '0') {
                        check = 0
                        break
                    }
                }
                if (check == 1) {
                    resultTextView.text = saved.substring(0, i)
                }
                break
            }
        }

    }

    fun delete(clickedview: View) {

        var del = resultTextView.text.toString()

        if (del.isEmpty()) {
            return
        }

        resultTextView.text = del.dropLast(1)


    }

    fun clear (clickedview: View) {

        resultTextView.text = ""

    }



}