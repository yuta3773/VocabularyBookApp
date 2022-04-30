package com.example.vocabularybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocabularybook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //学習画面への遷移
        binding.studyButton.setOnClickListener {
            intent = Intent(this, StudyActivity::class.java)
            startActivity(intent)
        }

        //単語追加画面への遷移
        binding.additionButton.setOnClickListener {
            intent = Intent(this, AdditionActivity::class.java)
            startActivity(intent)
        }
    }
}