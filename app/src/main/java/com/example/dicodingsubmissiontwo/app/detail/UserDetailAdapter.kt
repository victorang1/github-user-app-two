package com.example.dicodingsubmissiontwo.app.detail

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.app.detail.fragment.FollowerFragment
import com.example.dicodingsubmissiontwo.app.detail.fragment.FollowingFragment
import com.example.dicodingsubmissiontwo.model.GithubUser

class UserDetailAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val titles = intArrayOf(R.string.tab_follower, R.string.tab_following)
    private var user: GithubUser? = null

    fun setUser(user: GithubUser?) {
        this.user = user
    }

    override fun getCount(): Int = titles.count()

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment.newInstance(user)
            1 -> fragment = FollowingFragment.newInstance(user)
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(titles[position])
    }
}