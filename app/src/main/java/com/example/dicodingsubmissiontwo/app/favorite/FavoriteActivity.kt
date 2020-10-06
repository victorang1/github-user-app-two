package com.example.dicodingsubmissiontwo.app.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFavoriteBinding
    private lateinit var mAdapter: FavoriteAdapter
    private val mViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
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

    private fun initializeObserver() {
        mViewModel.getFavoriteItems().observe(this, Observer { favoriteItems ->
            mAdapter.setDataSet(favoriteItems)
        })
    }
}