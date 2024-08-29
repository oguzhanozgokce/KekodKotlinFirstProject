package com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.oguzhanozgokce.kekodkotlinfirstproject.MainActivity
import com.oguzhanozgokce.kekodkotlinfirstproject.R
import com.oguzhanozgokce.kekodkotlinfirstproject.common.gone
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.FragmentEgoBinding
import com.oguzhanozgokce.kekodkotlinfirstproject.common.setEnabled
import com.oguzhanozgokce.kekodkotlinfirstproject.common.visible

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
            setSwitchColors(uiState.isEgoChecked, binding.switchEgo)
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

    private fun setSwitchColors(isChecked: Boolean, switch: SwitchCompat) {
        val thumbColor = if (isChecked) R.color.green else R.color.black
        val trackColor = if (isChecked) R.color.green else R.color.black

        switch.thumbTintList = ContextCompat.getColorStateList(requireContext(), thumbColor)
        switch.trackTintList = ContextCompat.getColorStateList(requireContext(), trackColor)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}