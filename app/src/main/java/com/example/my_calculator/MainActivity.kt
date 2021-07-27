package com.example.my_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    var lastnum :Boolean=false
    var lastdec : Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //implementing Button to application Instead of OnClick Function
    fun onDigit(view :View)
    {
       tvinput.append((view as Button).text)
        lastnum=true
    }
    fun onClear(view :View){
        tvinput.text = ""
        lastnum=false
        lastdec=false
    }
    fun onDec(view: View){
        if(lastnum && !lastdec){
        tvinput.append(".")
        lastdec=true
            lastnum=false
        }
    }
    private  fun removedotzero(result :String) :String{
        var value=result
        if(result.contains(".0")){
            value=value.substring(0,result.length-2)
        }
        return value
    }
    fun onView(view: View)
    {  //if last thing is operator it will not run
        if(lastnum){
            var tvValue = tvinput.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue=tvValue.substring(startIndex = 1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]   //99
                    var two = splitValue[1]   //1--(99-1)
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvinput.text = removedotzero((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]   //99
                    var two = splitValue[1]   //1--(99-1)
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvinput.text = removedotzero((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]   //99
                    var two = splitValue[1]   //1--(99-1)
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvinput.text =removedotzero((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]   //99
                    var two = splitValue[1]   //1--(99-1)
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvinput.text = removedotzero((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e :ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
    //check not more than one operator is used in asub string
    fun onOperator(view: View){
        if(lastnum && !isoperatoradded(tvinput.text.toString())){
            tvinput.append((view as Button).text)
        lastdec=false
        lastnum=false}
    }
    private fun isoperatoradded(value :String) :Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") ||value.contains("*") || value.contains("-")
                    ||value.contains("+")
        }
    }
}