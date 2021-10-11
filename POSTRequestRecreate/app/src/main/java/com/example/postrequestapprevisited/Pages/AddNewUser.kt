package com.example.postrequestapprevisited.Pages

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.widget.Toast
import com.example.postrequestapprevisited.*
import kotlinx.android.synthetic.main.activity_add_new_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewUser : AppCompatActivity() {
    val apiInterface = APIClint().getClient()?.create(ApiInterface::class.java)
    lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)

        btnSave.setOnClickListener {
            val name=edNameAU.text.toString()
            val location=edLocationAU.text.toString()
            val user =DataItem(name,location)
            adduser(user,onResult = {
                edNameAU.setText("")
                edLocationAU.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun adduser(userOb:DataItem,onResult:()->Unit) {
        funProgressDialog()
        if (apiInterface!=null){
            apiInterface.addUserToApi(userOb).enqueue(object : Callback<DataItem?> {
                override fun onResponse(call: Call<DataItem?>, response: Response<DataItem?>) {
                    onResult
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "save Success!", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<DataItem?>, t: Throwable) {
                    onResult
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()

                }
            })
        }

    }
       fun goBack(view: android.view.View) {
        intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    fun funProgressDialog(){
        progressDialog = ProgressDialog(this@AddNewUser)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
    }

}