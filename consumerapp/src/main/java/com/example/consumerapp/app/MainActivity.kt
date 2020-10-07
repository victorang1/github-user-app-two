package com.example.consumerapp.app

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ActivityMainBinding
import com.example.core.FavoriteProviderConfig.Companion.CONTENT_URI
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: FavoriteAdapter
    private val mViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeAdapter()
        initializeObserver()
    }

    private fun initializeAdapter() {
        mAdapter = FavoriteAdapter(this, arrayListOf())
        val layoutManager = LinearLayoutManager(this)
        mBinding.rvFavorite.layoutManager = layoutManager
        mBinding.rvFavorite.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        mBinding.rvFavorite.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadFavoriteItems()
    }

    private fun initializeObserver() {
        mViewModel.getState().observe(this, Observer { state ->
            if (state == MainViewModel.SHOW_DATA) {
                mViewModel.getFavoriteItems().value?.let {
                    mAdapter.setDataSet(it)
                }
                if (mViewModel.getFavoriteItems().value?.count() == 0) {
                    mBinding.tvNoData.visibility = View.VISIBLE
                } else {
                    mBinding.tvNoData.visibility = View.GONE
                }
            }
        })
        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
        initContentResolverObserver()
    }

    private fun initContentResolverObserver() {
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                mViewModel.loadFavoriteItems()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
    }
}
