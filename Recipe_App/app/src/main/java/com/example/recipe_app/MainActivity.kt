package com.example.recipe_app

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        val author = findViewById<View>(R.id.editTextTextPersonName3) as EditText
        val inge = findViewById<View>(R.id.editTextTextPersonName4) as EditText
        val ins = findViewById<View>(R.id.editTextTextPersonName5) as EditText
        val savebtn = findViewById<View>(R.id.button) as Button

        savebtn.setOnClickListener {
            var f = books.UserDetails(title.text.toString(), author.text.toString(),
                inge.text.toString(),    ins.text.toString())

            addUser(f, onResult = {
                title.setText("")
                author.setText("")
                inge.setText("")
                ins.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }
    }

    fun addUser(userData: books.UserDetails, onResult: (books?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addUser(userData).enqueue(object : Callback<books> {
                override fun onResponse(
                    call: Call<books>,
                    response: Response<books>
                ) {
                    onResult(response.body())
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<books>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        }
    }

    fun viewreceipe(view: android.view.View) {
        intent = Intent(applicationContext, Newuser::class.java)
        startActivity(intent)
    }}
