package com.example.dicodingsubmissiontwo.app.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.ActivityUserDetailBinding
import com.example.dicodingsubmissiontwo.model.GithubUser

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val GITHUB_USER_DATA: String = "user_data"
    }

    private lateinit var mBinding: ActivityUserDetailBinding
    private lateinit var mSectionsAdapter: UserDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val githubUser = intent.getParcelableExtra<GithubUser>(GITHUB_USER_DATA)
        initializeAdapter(githubUser)
    }

    private fun initializeAdapter(user: GithubUser?) {
        mSectionsAdapter = UserDetailAdapter(this, supportFragmentManager)
        mSectionsAdapter.setUser(user)
        mBinding.viewPager.adapter = mSectionsAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }
}