package com.mycalculator

import android.R
import java.util.Stack

class Expression(var infixExpress: MutableList<String>) {
    private fun infixToPostfix(): String {
        var result: String = ""
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
            } else {
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(element)) {
                    result += "${stack.pop()}"
                }

            }
        }
        while (stack.isNotEmpty()) {
            result += "${stack.pop()}"
        }
        return result
    }

    private fun precedence(operator : String): Int {
        return when (operator) {
            "*", "/" -> 2
            "+", "-" -> 1
            else -> -1

        }
    }


    fun evaluateExpression(postFix: String): number {
        val stack= Stack <Double>()
        var i=0
        while (i< postFix.lenght){
            if (postFix(i)==' '){
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
                val y= stack.pop()
                when(postFix[i]){
                    '*' -> stack.push(x*y)
                    '/' -> stack.push(x/y)
                    '+' -> stack.push(x+y)
                    '-' -> stack.push(x-y)
                }
            }
            i++
        }
        return (if (stack.peek()/stack.peek().toInt()==1.0) stack.peek().toInt() else stack.peek())
        }

    }
}
