package com.example.vocabularybook

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.vocabularybook.databinding.ActivityStudyBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudyActivity : AppCompatActivity() {
    private var number = 0
    private lateinit var studyList: MutableList<VocabularyData>
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
            studyList = Gson().fromJson<MutableList<VocabularyData>>(extract, type) as MutableList<VocabularyData>
            //取り出した値の代入
            binding.englishQuestionText.text = studyList[number].english
        } else {
            binding.studyBackButton.isEnabled = false
            binding.studyJapaneseButton.isEnabled = false
            binding.studyNextButton.isEnabled = false
            AlertDialog.Builder(this)
                .setTitle("登録されている単語はありません\n単語を追加登録してください")
                .setPositiveButton("OK"){ dialog, which -> }
                .show()
        }

        //前の単語
        binding.studyBackButton.setOnClickListener {
            if (number != 0) {
                binding.studyNextButton.isEnabled = true
                number -= 1
                binding.englishQuestionText.text = studyList[number].english
            } else {
                binding.studyBackButton.isEnabled = false
                AlertDialog.Builder(this)
                    .setTitle("これ以上は戻れません")
                    .setPositiveButton("OK"){ dialog, which -> }
                    .show()
            }
        }

        //次の単語
        binding.studyNextButton.setOnClickListener {
            if (studyList.size > number + 1) {
                binding.studyBackButton.isEnabled = true
                number += 1
                binding.englishQuestionText.text = studyList[number].english
                binding.japaneseQuestionText.text = null
            } else {
                binding.studyBackButton.isEnabled = true
                binding.studyNextButton.isEnabled = false
                AlertDialog.Builder(this)
                    .setTitle("登録されている単語はありません\n単語を追加登録してください")
                    .setPositiveButton("OK"){ dialog, which -> }
                    .show()
            }
        }

        //日本語表示
        binding.studyJapaneseButton.setOnClickListener {
            binding.japaneseQuestionText.text = studyList[number].japanese
        }


        //HOME画面に遷移
        binding.studyHomeButton.setOnClickListener { finish() }
    }
}