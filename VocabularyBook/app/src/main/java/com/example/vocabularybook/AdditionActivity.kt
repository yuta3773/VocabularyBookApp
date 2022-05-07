package com.example.vocabularybook

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.core.content.edit
import com.example.vocabularybook.databinding.ActivityAdditionBinding
import com.google.gson.Gson

class AdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding
    private lateinit var vocabularyData: VocabularyData
    private var vocabularyDataList: MutableList<VocabularyData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //タップ時入力データ保存
        binding.addKeepButton.setOnClickListener {
            //データの入力条件分岐。Dialog表示
            if (binding.inputAddEnglishText.text.toString() != "" && binding.inputAddJapaneseText.text.toString() != "") {
                vocabularyData = VocabularyData(english = binding.inputAddEnglishText.text.toString(),
                    japanese = binding.inputAddJapaneseText.text.toString())
                vocabularyDataList.add(vocabularyData)
                //保存処理の呼び出し
                onSaveTapped()
                //保存完了Dialog
                AlertDialog.Builder(this)
                    .setTitle("保存完了")
                    .setPositiveButton("OK"){ dialog, which -> }
                    .show()
                //入力した文字を空にする
                binding.inputAddEnglishText.text = null
                binding.inputAddJapaneseText.text = null
            } else {
            AlertDialog.Builder(this)
                .setTitle("ERROR！")
                .setMessage("入力してください")
                .setPositiveButton("OK"){ dialog, which -> }
                .show()
            }
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