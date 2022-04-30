package com.example.vocabularybook

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VocabularyData(var english: String, var japanese: String) : Parcelable
