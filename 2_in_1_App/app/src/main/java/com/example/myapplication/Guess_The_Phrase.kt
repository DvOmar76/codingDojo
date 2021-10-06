package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_guess_the_phrase.*

class Guess_The_Phrase : AppCompatActivity() {
    var message= ArrayList<String>()
    lateinit var edGuessPhares: EditText
    lateinit var btnuessPharase: Button
    private lateinit var clGuessPharse: ConstraintLayout
    lateinit var tvPharss: TextView
    private lateinit var highscorView: TextView

    var guesses=10
    var highScore=0
    var answer="omaro"
    var guessed_Letter=""
    var phraseStar=convertLiterToStar()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_the_phrase)



        highscorView = findViewById(R.id.tvScore)
        loadData()
        edGuessPhares=findViewById(R.id.etInputUser)
        btnuessPharase=findViewById(R.id.button)
        clGuessPharse=findViewById(R.id.cLMainGuessPhrase)
        rcGuessPhrase.adapter=GuessThePhraseAdapter(this,message)
        rcGuessPhrase.layoutManager= LinearLayoutManager(this)
        tvPharss=findViewById(R.id.tvGuessPhrase)
        tvPharss.setText("Phrase : $phraseStar")

        btnuessPharase.setOnClickListener {

            if(guesses>0){
                addMessage()
            }
            else {
                message.add("You lose - The correct answer was $answer")
                message.add("Game Over")
                customAlert("You lose...\nThe correct answer was $answer.\n\nPlay again?")
            }
        }

    }

    private fun updateScore(){

        if(guesses>highScore) highScore=guesses
        Snackbar.make(clGuessPharse, "NEW HIGH SCORE!", Snackbar.LENGTH_LONG).show()

    }

    fun addMessage(){
        val guess = edGuessPhares.text.toString()
        if(guess.isEmpty()){
            Snackbar.make(clGuessPharse, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            if (guess ==answer || phraseStar==answer) {
                customAlert("You win!\n\nPlay again?")
                clearEditTextAndAddHint("Guess full phrase")
            }
            else {
                if(edGuessPhares.hint=="Guess full phrase"){
                    message.add("wrong guess: $guess")
                    clearEditTextAndAddHint("enter litter")

                }
                else{

                    if(guess.isEmpty()) Snackbar.make(clGuessPharse, "Please enter some text", Snackbar.LENGTH_LONG).show()
                    else{
                        fundInText(edGuessPhares.text.toString())
                        guesses--
                        message.add("guess remaining $guesses")
                        clearEditTextAndAddHint("Guess full phrase")
                    }


                }

            }

        }

        rcGuessPhrase.scrollToPosition(message.size - 1)
        saveData()

    }
    override fun recreate(){
        super.recreate()
        guesses=10
        highScore=0
        answer="omaro"
        guessed_Letter=""
        phraseStar=convertLiterToStar()
        message.clear()


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

    private fun customAlert(title: String){
        updateScore()
        saveData()
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
    fun clearEditTextAndAddHint(hint:String){
        edGuessPhares.text.clear()
        edGuessPhares.clearFocus()
        etInputUser.setHint("$hint")
    }

    fun convertLiterToStar(): String {
        var stars=""
        //add stars
        for (i in answer.indices){
            stars+="*"
        }

        return stars
    }

    fun fundInText(litter:String){
        var count=0
        var found=false
        for (i in answer.indices){
            if (answer[i]==litter.single()){
                count++
                phraseStar = phraseStar.substring(0, i) + litter + phraseStar.substring(i + 1)

                found=true
            }
        }

        if(found==false){
            message.add("No Found $litter")
        }
        else{
            guessed_Letter+="$litter "

        }
        tvPharss.setText("Phrase : $phraseStar \n Guessed Letter $guessed_Letter ")
        message.add("Found $count $litter(s)")

        if (phraseStar==answer) {
            customAlert("You win!\n\nPlay again?")
            clearEditTextAndAddHint("Guess full phrase")

        }
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences("preference_file_key", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putInt("highScore",highScore)
        }.apply()
    }


    fun loadData(){
        val sharedPreferences = getSharedPreferences("preference_file_key",Context.MODE_PRIVATE)
        highScore=sharedPreferences.getInt("highScore",0)
        highscorView.setText("High Score: $highScore")

    }


}