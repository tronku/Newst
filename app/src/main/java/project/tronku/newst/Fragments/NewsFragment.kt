package project.tronku.newst.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import com.squareup.picasso.Picasso
import project.tronku.newst.Models.NewsModel

import project.tronku.newst.R

class NewsFragment : Fragment() {

    val TAG = "NEWS_FRAGMENT"

    companion object {
        fun newInstance(news: NewsModel): Fragment {
            val fragment = NewsFragment()
            val bundle = bundleOf(
                "headline" to news.headline,
                "summary" to news.summary,
                "source" to news.source.name,
                "image" to news.imgUrl,
                "description" to news.description
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val headline = arguments!!["headline"] as String
        val summary = arguments!!["summary"] as String?
        val source = arguments!!["source"] as String
        val imgUrl = arguments!!["image"] as String
        val description = arguments!!["description"] as String

        Log.d(TAG, "onCreateView")
        Log.d(TAG, headline)

        val headlineView = view.findViewById<TextView>(R.id.feed_news_headline)
        val summaryView = view.findViewById<TextView>(R.id.feed_news_summary)
        val sourceView = view.findViewById<TextView>(R.id.feed_news_source)
        val image = view.findViewById<ImageView>(R.id.feed_image_large)

        headlineView.text = headline
        summaryView.text = summary ?: description
        sourceView.text = source
        Picasso.get().load(imgUrl).placeholder(R.drawable.news_placeholder).into(image)

        return view
    }

}
