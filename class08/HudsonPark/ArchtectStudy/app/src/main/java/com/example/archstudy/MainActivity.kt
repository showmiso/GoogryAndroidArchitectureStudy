package com.example.archstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView() // 뷰 초기화
        initEvent() // 이벤트 처리
    }

    private fun initEvent() {
        btnSearch.setOnClickListener {
            // 검색 버튼 클릭 시
            var query = edtQuery.text.toString()
            if (query.isEmpty()) {
                Toast.makeText(this, "검색어를 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 네이버 영화 정보 API 요청
                getQueryResult(edtQuery.text.toString()).let {
                    rvMovieList.adapter = MovieListAdapter(it) // 결과값 어댑터에 전달
                }
            }
        }
    }

    private fun initView() {
        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)
    }

    private fun getQueryResult(query : String): List<Movie>{






        return mutableListOf()
    }
}
