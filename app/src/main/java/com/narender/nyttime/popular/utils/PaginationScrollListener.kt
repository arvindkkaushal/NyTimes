package com.narender.nyttime.popular.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class PaginationScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    internal lateinit var layoutManager: LinearLayoutManager

    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager
     */
    init {
        this.layoutManager = layoutManager
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && isPaginationValid()) {
                loadMoreItems()
            }
        }

    }

    abstract fun isLoading(): Boolean

    protected abstract fun loadMoreItems()

    abstract fun getTotalPageCount(): Int

    abstract fun isLastPage(): Boolean

    abstract fun isPaginationValid(): Boolean

}