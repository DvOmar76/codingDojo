package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_number_guessing_game.*
import kotlin.random.Random

class Number_Guessing_Game : AppCompatActivity() {
    var list= mutableListOf<String>()
    lateinit var edGuess: EditText
    lateinit var btnNumberGuess: Button
    private lateinit var clNumberGuess: ConstraintLayout
    lateinit var textView: TextView
    var randomNumber=0
    var guesses=3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guessing_game)
        edGuess=findViewById(R.id.etNumberGuess)
        btnNumberGuess=findViewById(R.id.btnGuessNum)
        list= mutableListOf()
        clNumberGuess=findViewById(R.id.cLMainNumber)
        rcGuessNum.adapter=NumberGuessingRVAdapter(this,list)
        rcGuessNum.layoutManager= LinearLayoutManager(this)
        textView=findViewById(R.id.tvGuessNum)
        textView.setText("guess a number between 0 and 11")
        randomNumber= Random.nextInt(0,11)
        val rnd = Random.Default
        btnNumberGuess.setOnClickListener {
            var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            btnNumberGuess.setBackgroundColor(color)
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.meGGameNumber -> {
                recreate()
                return true
            }
            R.id.meGGuess_the_phrase -> {
                startActivity(Intent(this,Guess_The_Phrase::class.java))
                return true
            }
            R.id.MainMenu -> {
                startActivity(Intent(this,MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
     override fun recreate(){
        super.recreate()
          randomNumber=0
          guesses=3
         list.clear()


     }
    private fun addMessage(){
        val guess = edGuess.text.toString()
        if(guess.isEmpty()){
            Snackbar.make(clNumberGuess, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            if (guess.toInt()==randomNumber) customAlert("You win!\\n\\nPlay again?")
            else {
                guesses--
                list.add("You guessed $guess")
                list.add("You have $guesses guesses left")
            }

        }
        edGuess.text.clear()
        edGuess.clearFocus()
        // for refresh recyclerView
        rcGuessNum.adapter?.notifyDataSetChanged()
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
