package com.oguzhanozgokce.kekodkotlinfirstproject.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.oguzhanozgokce.kekodkotlinfirstproject.R
import com.oguzhanozgokce.kekodkotlinfirstproject.common.CardSetup
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.CardInputResultBinding
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.FragmentDivisionBinding


class DivisionFragment : Fragment(), CardSetup {

    private var _binding: FragmentDivisionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDivisionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCard(binding.divisionCard1)
        setupCard(binding.divisionCard2)
        setupCard(binding.divisionCard3)
        setupCard(binding.divisionCard4)
        setupCard(binding.divisionCard5)
    }

    override fun setupCard(binding: CardInputResultBinding) {
        val firstNumberEditText = binding.firstNumber
        val secondNumberEditText = binding.secondNumber
        val resultTextView = binding.resultTextView
        val operationImageView = binding.operationImage

        operationImageView.setImageResource(R.drawable.ic_check)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateResult(firstNumberEditText, secondNumberEditText, resultTextView)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        firstNumberEditText.addTextChangedListener(textWatcher)
        secondNumberEditText.addTextChangedListener(textWatcher)
    }

    override fun calculateResult(firstNumberEditText: EditText, secondNumberEditText: EditText, resultTextView: TextView) {
        val firstNumber = firstNumberEditText.text.toString().toIntOrNull()
        val secondNumber = secondNumberEditText.text.toString().toIntOrNull()

        if (firstNumber != null && secondNumber != null) { // İki sayı da girildiyse
            val result = if (secondNumber != 0) {
                division(firstNumber, secondNumber)
            } else {
                "Error"
            }
            resultTextView.text = result.toString()
        } else {
            resultTextView.text = ""
        }
    }

    private fun division(a: Int, b: Int): Int {
        return a / b
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}