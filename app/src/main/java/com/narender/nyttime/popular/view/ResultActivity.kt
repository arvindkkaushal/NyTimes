package com.narender.nyttime.popular.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.narender.nyttime.R
import com.narender.nyttime.databinding.LayoutMainBinding
import com.narender.nyttime.popular.utils.PaginationScrollListener
import com.narender.nyttime.popular.viewModel.ResultActivityViewModel
import com.sevevpeak.narender.utils.PAGE_START


class ResultActivity : AppCompatActivity() {
    private var errorSnackbar: Snackbar? = null
    private var noMoreSnackbar: Snackbar? = null
    private lateinit var binding: LayoutMainBinding
    private lateinit var viewModel: ResultActivityViewModel
    private var isLastPage = false
    private val TOTAL_PAGES = 3
    private var isLoading = true
    private val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.layout_main)
        binding.recyelerView.layoutManager = linearLayoutManager
        viewModel = ViewModelProviders.of(this).get(ResultActivityViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMsg -> if (errorMsg != null) showError(errorMsg) else hideError() })
        viewModel.noMoreMessage.observe(this, Observer { noMoreMsg -> if (noMoreMsg != null) showNoMoreMsg(noMoreMsg) else hideNoMore() })
        binding.viewModel = viewModel
        //set Pagination
        setScrollListner(binding.recyelerView, linearLayoutManager)

    }

    private fun setScrollListner(recyelerView: RecyclerView?, linearLayoutManager: LinearLayoutManager) {
        recyelerView?.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                PAGE_START++
                viewModel.loadResult()
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isPaginationValid(): Boolean {
                return if (PAGE_START < TOTAL_PAGES) true else false
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }


    private fun showNoMoreMsg(@StringRes errorMessage: Int) {
        noMoreSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        noMoreSnackbar?.setAction(R.string.dismiss, View.OnClickListener { hideNoMore() })
        noMoreSnackbar?.show()
    }

    private fun hideNoMore() {
        noMoreSnackbar?.dismiss()
    }
}