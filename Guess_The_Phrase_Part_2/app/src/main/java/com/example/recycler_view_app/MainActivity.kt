package com.example.recycler_view_app

import android.content.Context
import android.content.DialogInterface
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
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var message= ArrayList<String>()
    lateinit var editText: EditText
    lateinit var button: Button
    private lateinit var cLMain:ConstraintLayout
    lateinit var textView: TextView
    private lateinit var highscorView: TextView

    var guesses=10
    var highScore=0
    var answer="omaro"
    var guessed_Letter=""
    var phraseStar=convertLiterToStar()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        highscorView = findViewById(R.id.tvScore)
        loadData()
        editText=findViewById(R.id.etInputUser)
        button=findViewById(R.id.button)
        cLMain=findViewById(R.id.cLMain)
        recyclerView.adapter=RecyclerViewAdapter(this,message)
        recyclerView.layoutManager= LinearLayoutManager(this)
        textView=findViewById(R.id.textViewMain)
        textView.setText("Phrase : $phraseStar")

        button.setOnClickListener {

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
        Snackbar.make(cLMain, "NEW HIGH SCORE!", Snackbar.LENGTH_LONG).show()

    }

    fun addMessage(){
        val guess = editText.text.toString()
        if(guess.isEmpty()){
            Snackbar.make(cLMain, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            if (guess ==answer || phraseStar==answer) {
                customAlert("You win!\n\nPlay again?")
                clearEditTextAndAddHint("Guess full phrase")
            }
            else {
                if(editText.hint=="Guess full phrase"){
                    message.add("wrong guess: $guess")
                    clearEditTextAndAddHint("enter litter")

                }
                else{

                    if(guess.isEmpty()) Snackbar.make(cLMain, "Please enter some text", Snackbar.LENGTH_LONG).show()
                    else{
                        fundInText(editText.text.toString())
                        guesses--
                        message.add("guess remaining $guesses")
                        clearEditTextAndAddHint("Guess full phrase")
                    }


                }

            }

        }

        recyclerView.scrollToPosition(message.size - 1)
        saveData()

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
        editText.text.clear()
        editText.clearFocus()
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
        textView.setText("Phrase : $phraseStar \n Guessed Letter $guessed_Letter ")
        message.add("Found $count $litter(s)")

        if (phraseStar==answer) {
            customAlert("You win!\n\nPlay again?")
            clearEditTextAndAddHint("Guess full phrase")

        }
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences("preference_file_key",Context.MODE_PRIVATE)
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