package com.narender.mockData


object TestDataGenerator {
    fun getResultList(): List<Result> {
        var d1 = Result("World", "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times")

        var d2 = Result("World", "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times")

        var d3 = Result("World", "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times")

        var d4 = Result("World", "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times")

        return listOf(d1, d2, d3, d4)
    }


}