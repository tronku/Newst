package project.tronku.newst.Data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import project.tronku.newst.Models.NewsModel

class ApiFetch (val query: String?, val context: Context) {
    val TAG = "API_FETCH"
    var newsList = ArrayList<NewsModel>()
    val gson = Gson()
    var baseUrl = "https://newsapi.org/v2/top-headlines?country=in" + if (query!!.isNotEmpty()) "&$query" else "" + "&apiKey=2544f26a90c44ad2940a0d85cddbccc9"

    fun fillNewsList(): ArrayList<NewsModel> {
        val request = JsonObjectRequest(Request.Method.GET, baseUrl, null,
            Response.Listener { response ->
                val listTypeToken = object: TypeToken<ArrayList<NewsModel>>(){}.type
                newsList = gson.fromJson(response.getString("articles"), listTypeToken)
                Log.d(TAG, newsList.toString())
            },
            Response.ErrorListener { error ->
                Log.e(TAG, error.message)
            })

        val volleyRequestQueue = Volley.newRequestQueue(context)
        volleyRequestQueue.add(request)
        return newsList
    }

}