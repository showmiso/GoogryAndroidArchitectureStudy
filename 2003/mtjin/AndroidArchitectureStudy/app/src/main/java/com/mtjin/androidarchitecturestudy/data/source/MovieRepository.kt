package com.mtjin.androidarchitecturestudy.data.source

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieRepository {
    fun getSearchMovies(query: String, success: (List<Movie>) -> Unit, fail: (Throwable) -> Unit)
}