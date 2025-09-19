package com.mycalculator

import android.R
import android.util.Log
import java.util.Stack
import kotlin.math.pow

class Expression(var infixExpress: MutableList<String>) {
    var postFix: String=""
    private fun infixToPostfix() {
        var result = ""
        val stack = Stack<String>()
        for (element in infixExpress){
            if (element.all { it.isDigit() } || element.any { it == '.' }) {
                result += "$element "
            } else if (element == "(") {
                stack.push(element as String?)
            } else if (element == ")") {
                while (stack.peek() != "(" && stack.isNotEmpty()) {
                    result += "${stack.pop()} "
                }
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            }
            else {
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(element)) {
                    result += "${stack.pop()}"
                }
            stack.push(element)

            }
        }
        while (stack.isNotEmpty()) {
            result += "${stack.pop()}"
        }
        postFix=result
    }

    private fun precedence(operator : String): Int {
        return when (operator) {
            "%" -> 4
            "^" -> 3
            "×", "÷" -> 2
            "+", "-" -> 1
            else -> -1

        }
    }


    fun evaluateExpression(): Number {
        infixToPostfix()
        val stack= Stack <Double>()
        var i=0
        while (i< postFix.length){
            if (postFix[i]==' '){
                i++
                continue
            }
            else if (Character.isDigit(postFix[i])){
                var number=""
                while (Character.isDigit(postFix[i]) || postFix[i]=='.'){
                    number+= postFix[i]
                    i++
                }
                stack.push(number.toDouble())
            }
            else{
                val x= stack.pop()
                var y =1.0
                if (stack.isNotEmpty()){
                    y= stack.pop()
                }
                when(postFix[i]){
                    '×' -> stack.push(x*y)
                    '÷' -> stack.push(y/x)
                    '+' -> stack.push(x+y)
                    '-' -> stack.push(y-x)
                    '%' -> if (x<1) stack.push(x*100) else stack.push(x/100)
                    '^' -> stack.push(y.pow(x))
                }
            }
            i++
        }
        return (if (stack.peek()/stack.peek().toInt()==1.0) stack.peek().toInt() else stack.peek())

    }
}
