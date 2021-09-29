package com.example.recycler_view_app

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    var guesses=10
    var answer="omaro"
    var Guessed_Letter=""
    var pharseStar=convertLiterToStar()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText=findViewById(R.id.etInputUser)
        button=findViewById(R.id.button)
        cLMain=findViewById(R.id.cLMain)
        recyclerView.adapter=RecyclerViewAdapter(this,message)
        recyclerView.layoutManager= LinearLayoutManager(this)
        textView=findViewById(R.id.textViewMain)
        textView.setText("Phrase : $pharseStar")
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

    fun addMessage(){
        val guess = editText.text.toString()
        if(guess.isEmpty()){
            Snackbar.make(cLMain, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            if (guess ==answer || pharseStar==answer) {
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
                pharseStar = pharseStar.substring(0, i) + litter + pharseStar.substring(i + 1)

                found=true
            }
        }

        if(found==false){
            message.add("No Found $litter")
        }
        else{
            Guessed_Letter+="$litter "

        }
        textView.setText("Phrase : $pharseStar \n Guessed Letter $Guessed_Letter ")
        message.add("Found $count $litter(s)")

        if (pharseStar==answer) {
            customAlert("You win!\n\nPlay again?")
            clearEditTextAndAddHint("Guess full phrase")

        }
    }

}