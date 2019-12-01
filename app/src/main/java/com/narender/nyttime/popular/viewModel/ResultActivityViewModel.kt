package com.narender.nyttime.popular.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.narender.nyttime.R
import com.narender.nyttime.popular.model.Result
import com.narender.nyttime.popular.model.ResultResponse
import com.narender.nyttime.popular.network.ResultResponseProvider
import com.narender.nyttime.popular.view.adapter.ResultAdapter
import com.sevevpeak.narender.utils.PAGE_START
import com.sevevpeak.narender.utils.PERIODS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultActivityViewModel : ViewModel() {

    val resultAdapter: ResultAdapter = ResultAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val noMoreMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadResult() }


    init {
        loadResult()
    }

    fun loadResult() {
        GlobalScope.launch(Dispatchers.Main) {

            val repository = ResultResponseProvider.provideSearchRepository()
            if (PAGE_START < PERIODS.size) {
                repository.getArticles(PERIODS[PAGE_START])
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe { onRetrieveResultListStart() }
                        .doOnTerminate { onRetrieveResultListFinish() }
                        .subscribe(
                                { result -> onRetrievePostListSuccess(result) },
                                { e -> onRetrieveResultListError() }
                        )
            } else {
                onRetrieveResultListNoMoreMsg()
            }

        }

    }


    private fun onRetrieveResultListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveResultListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: Any) {
        if (postList is ResultResponse) {
            resultAdapter.updateResultList(postList.results)
        } else if (postList is List<*>) {
            resultAdapter.updateResultList(postList as List<Result>)
        }

    }

    private fun onRetrieveResultListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveResultListNoMoreMsg() {
        noMoreMessage.value = R.string.no_more_msg
    }


}