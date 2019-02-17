package project.tronku.newst.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import project.tronku.newst.Fragments.NewsFragment
import project.tronku.newst.Models.NewsModel

class NewsPagerAdapter(fm: FragmentManager, private val newsList: ArrayList<NewsModel>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return NewsFragment.newInstance(newsList[position])
    }

    override fun getCount(): Int {
        return newsList.size
    }
}