package com.example.dicodingsubmissiontwo.app.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.app.detail.UserDetailActivity
import com.example.dicodingsubmissiontwo.databinding.ActivityFavoriteBinding
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.model.GithubUser
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity(), FavoriteAdapter.OnItemClickCallback {

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
        mAdapter.setOnItemClickListener(this)
        mBinding.rvFavorite.adapter = mAdapter
    }

    private fun initializeObserver() {
        mViewModel.getFavoriteItems().observe(this, Observer { favoriteItems ->
            mAdapter.setDataSet(favoriteItems)
            if (favoriteItems.isNullOrEmpty()) {
                mBinding.tvNoData.visibility = View.VISIBLE
            }
            else mBinding.tvNoData.visibility = View.GONE
        })
    }

    override fun onItemClicked(data: FavoriteUser) {
        val intent = Intent(this@FavoriteActivity, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.GITHUB_USER_DATA, mViewModel.getGithubUserObject(data))
        intent.putExtra(UserDetailActivity.IS_FAVORITE, true)
        startActivity(intent)
    }
}