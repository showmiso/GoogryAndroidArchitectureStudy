package com.ironelder.androidarchitecture.data


data class TotalModel(
    val display: Int,
    val items: ArrayList<ResultItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class ResultItem(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val subtitle: String,
    val title: String,
    val description: String?,
    val author: String
)