package com.example.dicodingsubmissiontwo.app.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dicodingsubmissiontwo.databinding.FragmentFollowerBinding
import com.example.dicodingsubmissiontwo.model.GithubUser

class FollowerFragment: Fragment() {

    companion object {
        private val ARG_USER = "user"

        fun newInstance(user: GithubUser?): FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mBinding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}