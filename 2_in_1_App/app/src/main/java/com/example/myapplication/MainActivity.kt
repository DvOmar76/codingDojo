package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnNumGuess: Button
    lateinit var btnGuessPhr: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNumGuess=findViewById(R.id.btnNumberGuessing)
        btnGuessPhr=findViewById(R.id.btnGuessThePhrase)


        btnNumGuess.setOnClickListener {
            startActivity(Intent(this,Number_Guessing_Game::class.java))
        }
        btnGuessPhr.setOnClickListener {
            startActivity(Intent(this,Guess_The_Phrase::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.meGameNumber -> {
                startActivity(Intent(this,Number_Guessing_Game::class.java))
                return true
            }
            R.id.meGuess_the_phrase -> {
                startActivity(Intent(this,Guess_The_Phrase::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}