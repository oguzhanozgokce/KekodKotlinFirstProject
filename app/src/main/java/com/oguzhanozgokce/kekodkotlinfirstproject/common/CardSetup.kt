package com.oguzhanozgokce.kekodkotlinfirstproject.common

import android.widget.EditText
import android.widget.TextView
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.CardInputResultBinding

interface CardSetup {
    fun setupCard(binding: CardInputResultBinding)
    fun calculateResult(firstNumberEditText: EditText, secondNumberEditText: EditText, resultTextView: TextView)
}
