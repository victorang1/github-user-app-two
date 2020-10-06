package com.example.dicodingsubmissiontwo.app.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.ActivityUserDetailBinding
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.service.ApiConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val GITHUB_USER_DATA: String = "user_data"
        const val IS_FAVORITE: String = "is_favorite"
    }

    private lateinit var mBinding: ActivityUserDetailBinding
    private lateinit var mSectionsAdapter: UserDetailAdapter
    private var user: GithubUser = GithubUser()
    private val mViewModel: UserDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val githubUser = intent.getParcelableExtra<GithubUser>(GITHUB_USER_DATA) as GithubUser
        val isFromFavorite = intent.getBooleanExtra(IS_FAVORITE, false)
        if (mViewModel.getState().value == null) {
            mViewModel.getUserData(githubUser)
            if(!isFromFavorite) mViewModel.checkFavoriteFromStorage(githubUser)
        }
        initializeListener()
        initializeAdapter(githubUser)
        initializeObserver()
    }

    private fun initializeListener() {
        mBinding.fabFavorite.setOnClickListener(this)
    }

    private fun initializeAdapter(user: GithubUser?) {
        mSectionsAdapter = UserDetailAdapter(this, supportFragmentManager)
        mSectionsAdapter.setUser(user)
        mBinding.viewPager.adapter = mSectionsAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }

    private fun initializeObserver() {
        mViewModel.getState().observe(this, Observer { state ->
            if (state == ApiConfig.REQUEST_SUCCESS) {
                val userData = mViewModel.getUserData().value
                mBinding.user = userData ?: loadUserError()
                user = userData ?: loadUserError()
                Glide.with(this)
                    .load(mBinding.user?.avatar)
                    .apply(RequestOptions.errorOf(R.drawable.ic_person_white))
                    .into(mBinding.civAvatar)
            } else if (state == ApiConfig.REQUEST_ERROR) {
                mBinding.user = loadUserError(mViewModel.getErrorMessage().value)
            } else if (state == UserDetailViewModel.ACTION_SUCCESS) {
                mViewModel.checkFavoriteFromStorage(user)
                val message =
                    if (isFavorite()) getString(R.string.text_success_remove_favorite) else getString(
                        R.string.text_success_add_favorite
                    )
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.getFavoriteStatus().observe(this, Observer {
            val drawableId = if (it) R.drawable.ic_delete else R.drawable.ic_favorite
            mBinding.fabFavorite.setImageResource(drawableId)
            mBinding.fabFavorite.visibility = View.VISIBLE
        })
        mViewModel.getLoadingStatus().observe(this) { isLoading ->
            mBinding.isLoading = isLoading
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab_favorite -> showDialog()
        }
    }

    private fun loadUserError(textError: String? = resources.getString(R.string.text_error)): GithubUser {
        return GithubUser(
            0,
            textError ?: resources.getString(R.string.text_error),
            ""
        )
    }

    private fun showDialog() {
        val message = if (isFavorite()) getString(R.string.text_fab_remove_favorite)
        else getString(R.string.text_fab_add_favorite)
        val mBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setTitle(getString(R.string.text_confirmation))
            .setMessage(message)
            .setPositiveButton(getString(R.string.text_dialog_positive)) { _, _ ->
                mViewModel.runFavoriteAction(user)
            }
            .setNegativeButton(getString(R.string.text_dialog_negative)) { dialog, _ ->
                dialog.cancel()
            }
        val alertDialog = mBuilder.create()
        alertDialog.show()
    }

    private fun isFavorite(): Boolean {
        return mViewModel.getFavoriteStatus().value == true
    }
}