package com.example.post_updated

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Update_delete : AppCompatActivity() {
    lateinit var user_id: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)
        val name = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val location = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        user_id = findViewById<View>(R.id.editTextTextPersonName3) as EditText
        val updatebtn = findViewById<View>(R.id.update_button) as Button
        val deletebtn = findViewById<View>(R.id.delete_button) as Button


        updatebtn.setOnClickListener {
            val id = user_id.text.toString().toInt()

            var f = Users.UserDetails(name.text.toString(), location.text.toString(), id)
            updateUser(f, id, onResult = {
                name.setText("")
                location.setText("")
                Toast.makeText(applicationContext, "update Success!", Toast.LENGTH_SHORT).show();

            })
        }
        deletebtn.setOnClickListener {
            val id = user_id.text.toString().toInt()
            deleteUser(id)
        }
    }

    private fun updateUser(f: Users.UserDetails, id: Int, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@Update_delete)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.updateUser(id, f)?.enqueue(object : Callback<Users.UserDetails> {
                override fun onResponse(
                    call: Call<Users.UserDetails>,
                    response: Response<Users.UserDetails>
                ) {
                    onResult()
                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<Users.UserDetails>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        } else {
            Log.e("asdfjlkhf", "addSingleuser: ")
            Toast.makeText(applicationContext, "API error", Toast.LENGTH_SHORT).show();
        }

    }

    private fun deleteUser(id: Int) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@Update_delete)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.deleteUser(id)?.enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    Toast.makeText(applicationContext, "Delete Sucess!", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })

        }

    }
    fun update(view: View) {
        intent = Intent(applicationContext, Update_delete::class.java)
        startActivity(intent)
    }
}
