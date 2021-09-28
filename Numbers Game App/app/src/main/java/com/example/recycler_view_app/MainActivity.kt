package com.example.recycler_view_app

import android.content.DialogInterface
import android.graphics.Color
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var list= mutableListOf<String>()
    lateinit var editText: EditText
    lateinit var button: Button
    private lateinit var cLMain:ConstraintLayout
    lateinit var textView: TextView
    var randomNumber=0
    var guesses=3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText=findViewById(R.id.editTextTextPersonName)
        button=findViewById(R.id.button)
        list= mutableListOf()
        cLMain=findViewById(R.id.cLMain)
        recyclerView.adapter=RecyclerViewAdapter(this,list)
        recyclerView.layoutManager= LinearLayoutManager(this)
        textView=findViewById(R.id.textViewMain)
        textView.setText("guess a number between 0 and 11")
        randomNumber= Random.nextInt(0,11)
        val rnd = Random.Default
        button.setOnClickListener {
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        button.setBackgroundColor(color)
           if(guesses>0){
               addMessage()

           }
            else {
                list.add("You lose - The correct answer was $randomNumber")
                list.add("Game Over")
               customAlert("You lose...\\nThe correct answer was $randomNumber.\\n\\nPlay again?")
           }

        }


    }
    fun addMessage(){
        val guess = editText.text.toString()
        if(guess.isEmpty()){
            Snackbar.make(cLMain, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            if (guess.toInt()==randomNumber) customAlert("You win!\\n\\nPlay again?")
            else {
                guesses--
                list.add("You guessed $guess")
                list.add("You have $guesses guesses left")
                list.add("$randomNumber")
            }

        }
        editText.text.clear()
        editText.clearFocus()
        // for refresh recyclerView
        recyclerView.adapter?.notifyDataSetChanged()
    }
    private fun customAlert(title: String){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(title)
            .setPositiveButton("replay", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Game Over")
        alert.show()
    }

}