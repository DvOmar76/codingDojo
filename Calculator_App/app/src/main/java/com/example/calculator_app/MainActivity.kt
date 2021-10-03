package com.example.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isNewOp=true
    var oldNum=""
    var op="+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun numberEvent(view:View) {
        if (isNewOp) edDisplay.setText("")
        isNewOp=false
        var btnClick=edDisplay.text.toString()
        var btnSelect=view as Button
        when(btnSelect.id){
            btn0.id ->{btnClick+="0"}
            btn1.id ->{btnClick+="1"}
            btn2.id ->{btnClick+="2"}
            btn3.id ->{btnClick+="3"}
            btn4.id ->{btnClick+="4"}
            btn5.id ->{btnClick+="5"}
            btn6.id ->{btnClick+="6"}
            btn7.id ->{btnClick+="7"}
            btn8.id ->{btnClick+="8"}
            btn9.id ->{btnClick+="9"}
            btnDot.id ->{btnClick+="."}
            btnSign.id ->{
                var length=btnClick.length
                if(btnClick.startsWith("-")) btnClick=btnClick.subSequence(1,length).toString()
                else btnClick="-$btnClick"

            }



        }
        edDisplay.setText(btnClick)
    }
    fun operatorEvent(view: View){
        isNewOp=true
        oldNum=edDisplay.text.toString()
        var btnSelect:Button=view as Button
        when(btnSelect.id){
            btnAdd.id ->{op="+"}
            btnSubtract.id ->{op="-"}
            btnMultiply.id ->{op="*"}
            btnDivide.id ->{op="/"}

        }
    }
    fun clear(view: View){
        edDisplay.setText("0")
        isNewOp=true

    }
    fun equalEvent(view: View){
        var newNum:String=edDisplay.text.toString()
        var result=0.0
        when(op){
            "+"->{result=oldNum.toDouble() + newNum.toDouble()}
            "-"->{result=oldNum.toDouble() - newNum.toDouble()}
            "*"->{result=oldNum.toDouble() * newNum.toDouble()}
            "/"->{
                if (newNum.toInt()==0) Toast.makeText(applicationContext, "can't divide by 0", Toast.LENGTH_SHORT).show()
                else result=oldNum.toDouble() / newNum.toDouble() }
        }
        edDisplay.setText(result.toString())
    }
    fun delete(view: View){
        var length= edDisplay.length()
        if(length>0)
            edDisplay.text= edDisplay.text.subSequence(0,length-1) as Editable?

    }


}