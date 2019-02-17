package project.tronku.newst.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import project.tronku.newst.Adapters.NewsPagerAdapter
import project.tronku.newst.Data.ApiFetch
import project.tronku.newst.Fragments.NewsFragment
import project.tronku.newst.Models.NewsModel
import project.tronku.newst.NavBarActivity
import project.tronku.newst.R
import project.tronku.newst.UtilityClass.VerticalViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var view: View
    private lateinit var viewpager: VerticalViewPager
    private lateinit var adapter: NewsPagerAdapter
    private val TAG = "MAIN_ACTIVITY"
    private lateinit var newsList: ArrayList<NewsModel>
    private lateinit var fetchLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(android.R.id.content)
        viewpager = findViewById(R.id.feed_vertical_viewpager)
        fetchLayout = findViewById(R.id.fetchLayout)
        newsList = ArrayList()
        fillNewsList("")

        val shareButton = findViewById<ImageView>(R.id.share)
        shareButton.setOnClickListener {
            val currentNews = newsList[viewpager.currentItem]
            val title = currentNews.headline
            val description = currentNews.description
            val url = currentNews.newsUrl
            val news = "*$title*\n\n$description\n\nRead more -\n$url\n\n*Newst* - Kotlin based news app"

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, news)
                type = "text/plain"
            }

            startActivity(shareIntent)
        }

        val navBarButton = findViewById<ImageView>(R.id.navMenuOptions)
        navBarButton.setOnClickListener {
            val nav = Intent(this, NavBarActivity::class.java)
            startActivity(nav)
        }
    }

    override fun onBackPressed() {
        Snackbar.make(view, "Are you sure to exit?", Snackbar.LENGTH_LONG)
            .setAction("YES") { finishAffinity() }
            .setActionTextColor(Color.RED)
            .show()
    }



    private fun fillNewsList(query: String) {
        val gson = Gson()
        val baseUrl =
            "https://newsapi.org/v2/top-headlines?country=in" + if (query.isNotEmpty()) "&$query" else "" + "&apiKey=2544f26a90c44ad2940a0d85cddbccc9"
        val request = JsonObjectRequest(
            Request.Method.GET, baseUrl, null,
            Response.Listener { response ->
                val listTypeToken = object : TypeToken<ArrayList<NewsModel>>() {}.type
                newsList = gson.fromJson(response.getString("articles"), listTypeToken)
                Log.d(TAG, newsList.toString())
            },
            Response.ErrorListener { error ->
                Log.e(TAG, error.message)
            })

        val volleyRequestQueue = Volley.newRequestQueue(this)
        volleyRequestQueue.add(request)
        volleyRequestQueue.addRequestFinishedListener<JSONObject> {
            adapter = NewsPagerAdapter(supportFragmentManager, newsList)
            viewpager.adapter = adapter
            fetchLayout.visibility = View.INVISIBLE
        }
    }

}
