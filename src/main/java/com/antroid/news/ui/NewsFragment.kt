package com.antroid.news.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.antroid.news.R
import com.antroid.news.R.id.content
import com.antroid.news.R.id.story
import com.antroid.news.database.NewsDataTable
import com.antroid.news.model.DataItem
import com.antroid.news.ui.Constants.MENUO_OPTIONS_CONTENT
import com.antroid.news.ui.Constants.MENU_OPTIONS_STORY
import com.antroid.news.ui.adapters.BaseViewHolder
import com.antroid.news.ui.adapters.DataAdapter
import com.antroid.news.ui.adapters.NewsViewHolder
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    companion object{
        private const val TAG = "NEWSFRAGMENT"
    }

    private var newsFeedList = emptyList<NewsDataTable>()
    private lateinit var viewModel: NewsViewModel
    private lateinit var menu: MenuItem
    private val adapter: DataAdapter by lazy {
        object : DataAdapter(){
            override fun provideViewHolder(parentView: ViewGroup): BaseViewHolder<DataItem> {
                return NewsViewHolder.newInstance(parentView) as BaseViewHolder<DataItem>
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.let {
            viewModel = NewsViewModel.Factory(it.application).create(NewsViewModel::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        news_recycler_view.adapter = adapter
        if (!isNetworkAvailable(requireContext())) {
            makeText(requireContext(), "Network not Available", Toast.LENGTH_LONG).show()
            viewModel.fetchCachedNews()
        } else {
            viewModel.fetchNewsFeed()
        }
        setUpObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            story -> {
                val storyList = newsFeedList.filter { s -> s.type == MENU_OPTIONS_STORY }
                if (storyList.isEmpty()) {
                    makeText(
                        requireContext(),
                        "No Story News available!!",
                        Toast.LENGTH_LONG
                    ).show()
                    news_recycler_view.visibility = GONE
                    no_data_error_tv.visibility = VISIBLE

                } else {
                    adapter.updateData(storyList)
                    news_recycler_view.visibility = VISIBLE
                    no_data_error_tv.visibility = GONE
                }
                return true
            }
            content -> {
                val contentList = newsFeedList.filter { s -> s.type == MENUO_OPTIONS_CONTENT }
                if (contentList.isEmpty()) {
                    makeText(
                        requireContext(),
                        "No Content Package News available!!",
                        Toast.LENGTH_LONG
                    ).show()
                    news_recycler_view.visibility = GONE
                    no_data_error_tv.visibility = VISIBLE
                } else {
                    adapter.updateData(contentList)
                    news_recycler_view.visibility = VISIBLE
                    no_data_error_tv.visibility = GONE
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpObservers() {
        viewModel.newsFeed.observe(viewLifecycleOwner, Observer {newsFeed ->
            no_data_error_tv.visibility = GONE
            news_recycler_view.visibility = VISIBLE
            newsFeedList = newsFeed
            adapter.updateData(newsFeedList)
        })

        viewModel.newsFeedError.observe(viewLifecycleOwner, Observer {
            news_recycler_view.visibility = GONE
            no_data_error_tv.visibility = VISIBLE
        })
    }

    private fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }
}