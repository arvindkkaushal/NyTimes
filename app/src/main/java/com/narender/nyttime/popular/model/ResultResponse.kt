package com.narender.nyttime.popular.model

data class ResultResponse(val status: String, val copyright: String, val num_results: String, val results: List<Result>)