package project.tronku.newst.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import project.tronku.newst.Models.NewsModel

import project.tronku.newst.R

class FullNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_full_news, container, false)

        val headline = arguments!!["headline"] as String
        Toast.makeText(context, headline, Toast.LENGTH_SHORT).show()
        return view
    }


}
