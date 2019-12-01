package com.narender.nyttime.popular.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.narender.nyttime.R
import com.narender.nyttime.databinding.ItemCellBinding
import com.narender.nyttime.popular.model.Result
import com.narender.nyttime.popular.viewModel.ResultViewModel

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    private var resultList = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_cell, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    fun updateResultList(results: List<Result>) {
        resultList.addAll(results)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCellBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ResultViewModel()

        fun bind(result: Result) {
            viewModel.bind(result)
            binding.viewModel = viewModel
        }


    }

}