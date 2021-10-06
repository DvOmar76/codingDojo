package com.example.lambdapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//--------------------------WithOut parameters--------------------------
        // Lambda
        val age = { println("from Lambda you are 13 years ") }
        age()
        // Function
        fun ageFun() {
            println("from Function Hello ")
        }
        ageFun()

//--------------------------With one parameter fun 1--------------------------

        // Lambda
        val greeting = { yourAge: Int -> println("from Lambda your age $yourAge") }
        greeting(14)

        // Function
        fun greetingFun(yourAge: Int) {
            println("from Function your age $yourAge")
        }
        greetingFun(15)
//--------------------------With two parameters fun 2--------------------------

        // Lambda
        val sum = { num1: Int, num2: Int -> println("Lambda Sum =" + (num1 + num2)) }
        sum(4, 5)

        // Function
        fun sumFun(num1: Int, num2: Int) {
            println("Function Sum=" + (num1 + num2))
        }
        sumFun(15, 5)

//--------------------------return value fun 1--------------------------
        // Lambda
        val multi = { num1: Int, num2: Int -> (num1 * num2) }
        println("Lambda multi = " + multi(4, 5))

        // Function
        fun multiFun(num1: Int, num2: Int): Int {
            var multi = num1 * num2
            return multi
        }
        println("multi Function= " + multiFun(4, 5))
//--------------------------return value fun 2--------------------------
        // Lambda
        val add = { num1: Int, num2: Int -> (num1 + num2) }
        println("add Lambda= " + add(20, 5))

        // Function
        fun add(num1: Int, num2: Int): Int {
            var multi = num1 + num2
            return multi
        }
        println("add Function= " + add(20, 5))

        //----------------------class Players------------------------
        class Player {
            var name = ""
            var age = 0
            var height = ""

            constructor(name: String, age: Int, height: String) {
                this.name = name
                this.age = age
                this.height = height
            }

            override fun toString(): String {
                return name+" | "
            }

        }

        val players = listOf(
            Player("Bob", 23, "170"),
            Player("Sara", 24, "178"),
            Player("Jane", 25, "160"),
            Player("jon", 26, "180"),
            Player("ali", 21, "177"),
            Player("mohamed", 19, "170"),
            Player("Omar", 23, "178"),
            Player("khaled", 15, "160"),
            Player("Ford", 18, "180"),
            Player("Fred", 27, "177")
        )

        // Lambda
        val sort = { players.sortedBy { it.name }}
        // Function
        Log.d("12121",sort().toString())
         fun sortFun(){
           val players=players.sortedBy { it.name }
            var string=""
            for(ob in players){
                 string+=ob.name+" | "
             }
            Log.d("1212q",string)
         }
        sortFun()
    }

    }
