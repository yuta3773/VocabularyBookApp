package com.example.vocabularybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocabularybook.databinding.ActivityAdditionBinding

class AdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //HOME画面に遷移
        binding.addHomeButton.setOnClickListener { finish() }
    }
}