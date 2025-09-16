package com.mycalculator

import java.util.Stack

class Expression(infixExpression: MutableList<String>) {
    private fun infixToPostfix(): String {
        var result: String = ""
        val stack = Stack<String>()
        for (element in infixEpression) {
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
                while (stack.isNotEmpty() && precedence(stack.peek())>=precedence(element)) {
                    result += "${stack.pop()}"
                }

            }
        }
        while (stack.isNotEmpty()){
            result +="${stack.pop()}"
        }
        return result
    }

    private fun precedence(string: String): Int {
        return when(operator){
            "*","/" -> 2
            "+","-" -> 1
            else -> -1

        }
    }


    fun evaluateExpression(): Double {
        return 2.1

    }
}
