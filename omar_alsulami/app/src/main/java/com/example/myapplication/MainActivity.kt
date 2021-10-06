package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var tvCurrentBalance: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var edDeposit: EditText
    lateinit var edWithdraws: EditText
    lateinit var btnDeposit: Button
    lateinit var btnWithdraws: Button
    var currentBalance = 0
    var messages = ArrayList<String>()


//-------------------------------( menu)--------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.meClearLedger -> {
                messages.clear()
                recyclerView.adapter?.notifyDataSetChanged()

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
//-------------------------------( end menu)--------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvCurrentBalance = findViewById(R.id.tvCurrentBalance)
        recyclerView = findViewById(R.id.recyclerView)
        edDeposit = findViewById(R.id.editTextDeposit)
        edWithdraws = findViewById(R.id.editTextWithdraw)
        btnDeposit = findViewById(R.id.btnDeposit)
        btnWithdraws = findViewById(R.id.btnWithdraws)
//------------( call load balance)--------------------------------------
        loadData()
//------------------------------------------------------------------
        tvCurrentBalance.setText("current balance: $currentBalance ")
        recyclerView.adapter = RecyclerViewAdapter(messages)
        recyclerView.layoutManager = LinearLayoutManager(this)

//----------------------(Start Button deposit )---------------------------------
        btnDeposit.setOnClickListener {
            deposit(edDeposit.text.toString().toInt())

            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.scrollToPosition(messages.size - 1)

            edDeposit.text.clear()
            edDeposit.clearFocus()

            saveData()
        }
//----------------------(end Button deposit )---------------------------------

//----------------------(Start Button Withdraws )---------------------------------
        btnWithdraws.setOnClickListener {
            withdraws(edWithdraws.text.toString().toInt())
            recyclerView.adapter?.notifyDataSetChanged()

//----------------------------Automatically scroll----------------------------
            recyclerView.scrollToPosition(messages.size - 1)
//----------------------------------------------------------------------------
            edWithdraws.text.clear()
            edWithdraws.clearFocus()
            saveData()
        }
//----------------------(end  Button Withdraws )---------------------------------

    }

    fun deposit(amount: Int) {
        currentBalance+=amount
        messages.add("deposit: $amount")
        tvCurrentBalance.setText("current balance: $currentBalance ")

    }

    fun withdraws(amount: Int) {

        if(currentBalance>0){
            if ( currentBalance<amount ) {
                currentBalance -= amount+20
                messages.add("negative Balance free :20 ")
                tvCurrentBalance.setText("current balance: $currentBalance ")
                tvCurrentBalance.setTextColor(resources.getColor(R.color.red))

            }
            else {
                currentBalance -= amount
                messages.add("withdraw: $amount")
                tvCurrentBalance.setText("current balance: $currentBalance ")

            }
        }
        else Toast.makeText(applicationContext, "amount grater than your balance ", Toast.LENGTH_LONG).show()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("currentBalance", currentBalance)
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentBalance= savedInstanceState.getInt("currentBalance", 0)
        tvCurrentBalance.setText("current balance: $currentBalance ")
    }
//-----------------------------------(save the balance)--------------------------------------------

    fun saveData(){
        val sharedPreferences = getSharedPreferences("preference_file_key",Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putInt("currentBalance",currentBalance)
        }.apply()
    }

//-----------------------------------( end save the balance )--------------------------------------------

// -----------------------------------( load balance )--------------------------------------------

    fun loadData(){
        val sharedPreferences = getSharedPreferences("preference_file_key",Context.MODE_PRIVATE)
        currentBalance=sharedPreferences.getInt("currentBalance",0)
        tvCurrentBalance.setText("current balance: $currentBalance ")

    }

// -----------------------------------( end load balance )--------------------------------------------


}