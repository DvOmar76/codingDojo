package com.example.recipe_app
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Newuser : AppCompatActivity() {
    private var recipeDetails: List<books.UserDetails>? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_newuser)
            val responseText = findViewById<View>(R.id.textView2) as TextView

            getRecipes(onResult = {
                recipeDetails = it
                Log.e("Data", recipeDetails.toString())

                var stringToBePritined:String? = "";
                for(recipe in recipeDetails!!){
                    stringToBePritined = stringToBePritined +recipe.title + "\n"+recipe.author + "\n"+recipe.ingredients + "\n"+recipe.instructions+ "\n\n"
                }
                responseText.text= stringToBePritined
            } )
        }
    private fun getRecipes(onResult: (List<books.UserDetails>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@Newuser)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface.getRecipies()?.enqueue(object : Callback<List<books.UserDetails>> {
                override fun onResponse(
                    call: Call<List<books.UserDetails>>,
                    response: Response<List<books.UserDetails>>
                ) {
                    onResult(response.body())
                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<List<books.UserDetails>>, t: Throwable) {
                    onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}