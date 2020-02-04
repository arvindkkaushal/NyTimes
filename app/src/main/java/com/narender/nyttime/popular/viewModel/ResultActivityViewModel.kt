package com.narender.nyttime.popular.viewModel

import PAGE_START
import PERIODS
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.narender.nyttime.BuildConfig
import com.narender.nyttime.R
import com.narender.nyttime.popular.di.scope.ApplicationScope
import com.narender.nyttime.popular.model.ResultResponse
import com.narender.nyttime.popular.network.ResultResponseApi
import com.narender.nyttime.popular.view.adapter.ResultAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ResultActivityViewModel @Inject constructor(private val resultResponseApi: ResultResponseApi) : ViewModel() {

    val resultAdapter: ResultAdapter = ResultAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val noMoreMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadResult() }

    //use for add and clear all observer
    private val compositeDisposable = CompositeDisposable()


    fun loadResult() {
        if (PAGE_START < PERIODS.size) {
            compositeDisposable.add(resultResponseApi.getPosts(PERIODS[PAGE_START], BuildConfig.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveResultListStart() }
                    .doOnTerminate { onRetrieveResultListFinish() }
                    .subscribe(
                            { result -> onRetrievePostListSuccess(result) },
                            { e -> onRetrieveResultListError() }
                    ))

        } else {
            onRetrieveResultListNoMoreMsg()
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
        }

    }

    private fun onRetrieveResultListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveResultListNoMoreMsg() {
        noMoreMessage.value = R.string.no_more_msg
    }

    /**
     * Clear all Observer when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}