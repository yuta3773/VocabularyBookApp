package com.example.vocabularybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.vocabularybook.databinding.ActivityStudyBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //デフォルトの共有プリファレンスを習得
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //取得したデータを代入
        val extract = pref.getString("INPUT_DATA", "")
        //保存データの条件分岐
        if (extract != "") {
            //保存配列の取り出し
            val type = object : TypeToken<List<VocabularyData>>() {}.type
            var studyList = Gson().fromJson<MutableList<VocabularyData>>(extract, type) as MutableList<VocabularyData>
            //取り出した値の代入
            binding.englishQuestionText.text = studyList[0].english
            binding.japaneseQuestionText.text = studyList[0].japanese
        }

        //HOME画面に遷移
        binding.studyHomeButton.setOnClickListener { finish() }
    }
}