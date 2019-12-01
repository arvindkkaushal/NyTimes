package com.narender.nyttime.popular.model

data class Result(val section: String, val byline: String, val type: String, val title: String,
                  val published_date: String, val source: String)