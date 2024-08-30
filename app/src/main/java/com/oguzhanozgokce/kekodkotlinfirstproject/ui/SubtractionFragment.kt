package com.oguzhanozgokce.kekodkotlinfirstproject.ui

import android.os.Bundle
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
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.FragmentSubtractionBinding

class SubtractionFragment : Fragment(), CardSetup {
    private var _binding: FragmentSubtractionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubtractionBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCard(binding.subtractionFragmentCard1)
        setupCard(binding.subtractionFragmentCard2)
        setupCard(binding.subtractionFragmentCard3)
        setupCard(binding.subtractionFragmentCar4)
        setupCard(binding.subtractionFragmentCard5)

    }

    override fun setupCard(binding: CardInputResultBinding) {
        val firstNumberEditText = binding.firstNumber
        val secondNumberEditText = binding.secondNumber
        val resultTextView = binding.resultTextView

        val operationImageView = binding.operationImage
        operationImageView.setImageResource(R.drawable.ic_remove)

        firstNumberEditText.addTextChangedListener {
            calculateResult(firstNumberEditText, secondNumberEditText, resultTextView)
        }
        secondNumberEditText.addTextChangedListener {
            calculateResult(firstNumberEditText, secondNumberEditText, resultTextView)
        }
    }

    override fun calculateResult(firstNumberEditText: EditText, secondNumberEditText: EditText, resultTextView: TextView) {
        val firstNumber = firstNumberEditText.text.toString().toIntOrNull() ?: 0
        val secondNumber = secondNumberEditText.text.toString().toIntOrNull() ?: 0
        val result = subtract(firstNumber, secondNumber)
        resultTextView.text = result.toString()
    }

    private fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}