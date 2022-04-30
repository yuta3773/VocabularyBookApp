package com.example.vocabularybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.core.content.edit
import com.example.vocabularybook.databinding.ActivityAdditionBinding
import com.google.gson.Gson

class AdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding
    private lateinit var vocabularyData: VocabularyData
    var vocabularyDataList: MutableList<VocabularyData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //タップ時入力データ保存
        binding.addKeepButton.setOnClickListener {
            vocabularyData = VocabularyData(english = binding.inputAddEnglishText.text.toString(),
                                            japanese = binding.inputAddJapaneseText.text.toString())
            vocabularyDataList = mutableListOf(vocabularyData)
            //保存処理の呼び出し
            onSaveTapped()
        }

        //HOME画面に遷移
        binding.addHomeButton.setOnClickListener { finish() }
    }

    //保存処理
    private fun onSaveTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val str = Gson().toJson(vocabularyDataList)
        pref.edit {
            putString("INPUT_DATA", str)
                .commit()
        }
    }
}