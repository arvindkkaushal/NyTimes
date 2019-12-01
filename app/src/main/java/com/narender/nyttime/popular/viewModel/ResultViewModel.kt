package com.narender.nyttime.popular.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.narender.nyttime.popular.model.Result

class ResultViewModel : ViewModel() {
    private val title = MutableLiveData<String>()
    private val byLine = MutableLiveData<String>()
    private val source = MutableLiveData<String>()
    private val date = MutableLiveData<String>()

    fun bind(result: Result) {
        title.value = result.title
        byLine.value = result.byline
        source.value = result.source
        date.value = result.published_date
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getByLine(): MutableLiveData<String> {
        return byLine
    }

    fun getSource(): MutableLiveData<String> {
        return source
    }

    fun getDate(): MutableLiveData<String> {
        return date
    }

}