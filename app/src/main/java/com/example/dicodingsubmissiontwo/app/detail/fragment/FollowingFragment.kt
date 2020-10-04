package com.example.dicodingsubmissiontwo.app.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dicodingsubmissiontwo.databinding.FragmentFollowingBinding
import com.example.dicodingsubmissiontwo.model.GithubUser

class FollowingFragment: Fragment() {

    companion object {
        private val ARG_USER = "user"

        fun newInstance(user: GithubUser?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mBinding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}