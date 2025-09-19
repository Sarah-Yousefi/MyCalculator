package com.mycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val input= mutableListOf<String>()
    private var resultTextBox: TextView? = null
    private var infixExpression: Expression?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextBox= findViewById(R.id.resultTextBox)
    }
    @SuppressLint("SetTextI18n")
    fun onClick(button: View){
        val buttonText = (button as AppCompatButton).text.toString()
        when(buttonText){
            "=" -> {
                infixExpression= Expression(input)
                resultTextBox?.text= infixExpression!!.evaluateExpression().toString()
                input.clear()
                input.add(resultTextBox?.text.toString())
            }
            "CL" -> {
                input.clear()
                resultTextBox?.text=""
            }
            "C" -> {
                resultTextBox?.text="${resultTextBox?.text}".dropLast(1)
                if (resultTextBox?.text?.isNotEmpty()!! && resultTextBox?.text ==" ") resultTextBox?.text="${resultTextBox?.text}".dropLast(1)
                if (input.last().length==1) {
                    input.removeAt(input.lastIndex)
                }
                else {
                    input[input.lastIndex]= input.last().dropLast(1)
                }
            }
            else -> {
                if (Character.isDigit(buttonText[0]) || buttonText[0]=='.'){
                    if (input.isNotEmpty() && Character.isDigit(input.last()[0])){
                        input[input.lastIndex] =input.last()+ buttonText
                        resultTextBox?.text = "${resultTextBox?.text}$buttonText"
                    }
                    else{
                        input.add(buttonText)
                        resultTextBox?.text = "${resultTextBox?.text} $buttonText"
                    }
                }
                else {
                    input.add(buttonText)
                    resultTextBox?.text = "${resultTextBox?.text} $buttonText"
                }
            }
        }
    }
}