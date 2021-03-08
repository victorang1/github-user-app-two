package com.example.dicodingsubmissiontwo.app

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.app.detail.UserDetailActivity
import com.example.dicodingsubmissiontwo.databinding.ActivityMainBinding
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.service.ApiConfig.Companion.REQUEST_ERROR
import com.example.dicodingsubmissiontwo.service.ApiConfig.Companion.REQUEST_SUCCESS
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, UserAdapter.OnItemClickCallback {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: UserAdapter
    private val mViewModel: MainViewModel by viewModel()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (mViewModel.getState().value == null) {
            mBinding.isError = true
            mBinding.errorMessage = resources.getString(R.string.text_lets_search)
        }
        initializeAdapter()
        initializeObserver()
    }

    private fun initializeAdapter() {
        mAdapter = UserAdapter(this, mViewModel.getSearchResult().value ?: arrayListOf())
        val layoutManager = LinearLayoutManager(this)
        mBinding.rvUser.layoutManager = layoutManager
        mBinding.rvUser.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        mBinding.rvUser.adapter = mAdapter
        mAdapter.setOnItemClickCallback(this)
    }

    private fun initializeObserver() {
        mViewModel.getState().observe(this, Observer { state ->
            if (state == REQUEST_SUCCESS) {
                if (!mViewModel.getSearchResult().value.isNullOrEmpty()) {
                    mBinding.isError = false
                }
                else {
                    mBinding.isError = true
                    mBinding.errorMessage = resources.getString(R.string.text_no_user_found)
                }
                mAdapter.setDataSet(mViewModel.getSearchResult().value ?: arrayListOf())
            }
            else if (state == REQUEST_ERROR) {
                mBinding.isError = true
                mBinding.errorMessage = mViewModel.getErrorMessage().value ?: resources.getString(R.string.text_search_error)
            }
        })
        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
            if (isLoading) mBinding.groupError.visibility = View.GONE
        })
    }

    private fun initializeSearchView(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.text_search_hint)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        initializeSearchView(menu)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) mViewModel.searchUser(query)
        searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun onItemClicked(data: GithubUser) {
        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.GITHUB_USER_DATA, data)
        startActivity(intent)
    }
}