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
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ResultActivityViewModel : ViewModel() {

    val resultAdapter: ResultAdapter = ResultAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val noMoreMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadResult() }
    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    init {
        loadResult()
    }


    fun loadResult() {
        uiScope.launch() {
            callResultApi()
        }

    }

    suspend fun callResultApi() {
        val repository = ResultResponseProvider.provideSearchRepository()
        if (PAGE_START < PERIODS.size) {
            repository.getArticles(PERIODS[PAGE_START])
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { uiScope.launch(Main) { onRetrieveResultListStart() } }
                    .doOnTerminate { uiScope.launch(Main) { onRetrieveResultListFinish() } }
                    .subscribe(
                            { result -> uiScope.launch(Main) { onRetrievePostListSuccess(result) } },
                            { e -> uiScope.launch(Main) { onRetrieveResultListError() } }
                    )
        } else {
            uiScope.launch(Main) {
                onRetrieveResultListNoMoreMsg()
            }
        }
    }


    private suspend fun onRetrieveResultListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private suspend fun onRetrieveResultListFinish() {
        loadingVisibility.value = View.GONE
    }

    private suspend fun onRetrievePostListSuccess(postList: Any) {
        if (postList is ResultResponse) {
            resultAdapter.updateResultList(postList.results)
        }

    }

    private suspend fun onRetrieveResultListError() {
        errorMessage.value = R.string.post_error
    }

    private suspend fun onRetrieveResultListNoMoreMsg() {
        noMoreMessage.value = R.string.no_more_msg
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}