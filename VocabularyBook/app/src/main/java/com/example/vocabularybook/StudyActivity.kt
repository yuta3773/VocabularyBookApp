package com.example.vocabularybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocabularybook.databinding.ActivityStudyBinding

class StudyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //HOME画面に遷移
        binding.studyHomeButton.setOnClickListener { finish() }
    }
}