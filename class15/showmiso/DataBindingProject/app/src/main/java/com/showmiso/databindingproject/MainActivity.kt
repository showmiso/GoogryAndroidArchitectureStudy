package com.showmiso.databindingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.showmiso.databindingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // activity 와 xml 을 binding 객체로 묶었다.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User("miso", 30)
        binding.activity = this
    }

    // private 이면 안된다.
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
