package com.example.dicodingsubmissiontwo.app.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.ActivityUserDetailBinding
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.service.ApiConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val GITHUB_USER_DATA: String = "user_data"
    }

    private val TAG = "<RESULT>"

    private lateinit var mBinding: ActivityUserDetailBinding
    private lateinit var mSectionsAdapter: UserDetailAdapter
    private val mViewModel: UserDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val githubUser = intent.getParcelableExtra<GithubUser>(GITHUB_USER_DATA) as GithubUser
        if (mViewModel.getState().value == null) {
            mViewModel.getUserData(githubUser)
        }
        initializeAdapter(githubUser)
        initializeObserver()
    }

    private fun initializeAdapter(user: GithubUser?) {
        mSectionsAdapter = UserDetailAdapter(this, supportFragmentManager)
        mSectionsAdapter.setUser(user)
        mBinding.viewPager.adapter = mSectionsAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }

    private fun initializeObserver() {
        mViewModel.getState().observe(this) { state ->
            if (state == ApiConfig.REQUEST_SUCCESS) {
                mBinding.user = mViewModel.getUserData().value ?: loadUserError()
                Glide.with(this)
                    .load(mBinding.user?.avatar)
                    .apply(RequestOptions.errorOf(R.drawable.ic_person))
                    .into(mBinding.civAvatar)
            } else if (state == ApiConfig.REQUEST_ERROR) {
                mBinding.user = loadUserError()
            }
        }
        mViewModel.getLoadingStatus().observe(this) { isLoading ->
            mBinding.isLoading = isLoading
        }
    }

    private fun loadUserError(): GithubUser {
        return GithubUser(
            0,
            resources.getString(R.string.text_error),
            ""
        )
    }
}