package com.example.dicodingsubmissiontwo.app.detail.fragment.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.app.UserAdapter
import com.example.dicodingsubmissiontwo.databinding.FragmentFollowingBinding
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.service.ApiConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment: Fragment() {

    companion object {
        private const val ARG_USER = "user"

        fun newInstance(user: GithubUser?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mBinding: FragmentFollowingBinding
    private lateinit var mAdapter: UserAdapter
    private val mViewModel: FollowingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        mBinding.errorMessage = resources.getString(R.string.text_loading)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val githubUser = arguments?.getParcelable<GithubUser>(ARG_USER) as GithubUser
        if (githubUser.equals(null)) {
            mBinding.errorMessage = resources.getString(R.string.text_no_user_found)
        }
        else {
            mViewModel.loadFollowers(githubUser)
            initializeAdapter()
            initializeObserver()
        }
    }

    private fun initializeAdapter() {
        mAdapter = UserAdapter(activity!!.applicationContext, arrayListOf())
        val layoutManager = LinearLayoutManager(activity)
        mBinding.rvFollowing.layoutManager = layoutManager
        mBinding.rvFollowing.addItemDecoration(
            DividerItemDecoration(
                activity,
                layoutManager.orientation
            )
        )
        mBinding.rvFollowing.adapter = mAdapter
    }

    private fun initializeObserver() {
        mViewModel.getState().observe(viewLifecycleOwner) { state ->
            mBinding.errorMessage = ""
            if (state == ApiConfig.REQUEST_SUCCESS) {
                if (mViewModel.getUserFollowing().value.isNullOrEmpty()) {
                    mBinding.errorMessage = resources.getString(R.string.text_no_data)
                }
                mAdapter.setDataSet(mViewModel.getUserFollowing().value ?: arrayListOf())
            }
            else if (state == ApiConfig.REQUEST_ERROR) {
                mBinding.errorMessage = mViewModel.getErrorMessage().value ?: resources.getString(R.string.text_search_error)
            }
        }
    }
}