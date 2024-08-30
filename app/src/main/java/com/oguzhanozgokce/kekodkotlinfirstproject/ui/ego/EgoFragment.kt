package com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.oguzhanozgokce.kekodkotlinfirstproject.R
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.FragmentEgoBinding

class EgoFragment : Fragment() {
    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EgoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentEgoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.switchEgo.isChecked = uiState.isEgoChecked
            getOtherSwitches().forEachIndexed { index, switch ->
                switch.isChecked = when (index) {
                    0 -> uiState.isAdditionChecked
                    1 -> uiState.isSubtractionChecked
                    2 -> uiState.isMultiplicationChecked
                    3 -> uiState.isDivisionChecked
                    4 -> uiState.isModuloChecked
                    else -> false
                }
            }
            getOtherSwitches().forEach { switch ->
                switch.isEnabled = !uiState.isEgoChecked
            }
        }

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
        }

        getOtherSwitches().forEach { switch ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onOtherSwitchChanged(switch.id, isChecked)
            }
        }
    }

    private fun getOtherSwitches(): List<SwitchCompat> {
        return with(binding){
            listOf(
                switchAddition,
                switchSubtraction,
                switchMultiplication,
                switchDivision,
                switchModulo,
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}