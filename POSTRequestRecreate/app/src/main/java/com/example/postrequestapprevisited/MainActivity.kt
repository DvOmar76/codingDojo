package com.example.postrequestapprevisited

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postrequestapprevisited.Pages.AddNewUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface = APIClint().getClient()?.create(ApiInterface::class.java)
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }


    fun fetchData() {
        funProgressDialog()
        if (apiInterface != null) {
            apiInterface.getUser()?.enqueue(object : Callback<Array<DataItem>?> {
                override fun onResponse(
                    call: Call<Array<DataItem>?>,
                    response: Response<Array<DataItem>?>
                ) {
                    progressDialog.dismiss()
                    var text: String? = ""
                    for (user in response.body()!!) {
                        text += "pk : ${user.pk} \n ${user.name} \n ${user.location} \n\n\n "
                    }
                    tvScrollView.text = text
                }

                override fun onFailure(call: Call<Array<DataItem>?>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun funProgressDialog(){
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
    }
    fun toPageAddNewUser(view: android.view.View){
        intent= Intent(applicationContext, AddNewUser::class.java)
        startActivity(intent)
    }

}