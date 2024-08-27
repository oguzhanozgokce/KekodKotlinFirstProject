package com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.oguzhanozgokce.kekodkotlinfirstproject.MainActivity
import com.oguzhanozgokce.kekodkotlinfirstproject.R
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.FragmentEgoBinding
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.resetSwitches
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.setEnabled


class EgoFragment : Fragment() {
    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EgoViewModel by viewModels()
    private lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        const val ZERO = 0
        const val ONE = 1
        const val FIVE = 5
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentEgoBinding.inflate(inflater, container, false)
        val view = binding.root
        bottomNavigationView = (activity as MainActivity).getBottomNavigationView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.switchEgo.isChecked = uiState.isEgoChecked
            setSwitchColors(uiState.isEgoChecked, binding.switchEgo)

            binding.switchAddition.isChecked = uiState.isAdditionChecked
            binding.switchSubtraction.isChecked = uiState.isSubtractionChecked
            binding.switchMultiplication.isChecked = uiState.isMultiplicationChecked
            binding.switchDivision.isChecked = uiState.isDivisionChecked
            binding.switchModulo.isChecked = uiState.isModuloChecked

            getOtherSwitches().setEnabled(!uiState.isEgoChecked)
        }

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
        }

        getOtherSwitches().forEach { switch ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onOtherSwitchChanged(switch.id, isChecked)
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            updateBottomNavigationView(uiState)
        }
    }

    private fun getOtherSwitches(): List<SwitchMaterial> {
        return listOf(
            binding.switchAddition,
            binding.switchSubtraction,
            binding.switchMultiplication,
            binding.switchDivision,
            binding.switchModulo
        )
    }

    private fun updateBottomNavigationView(uiState: EgoUiState) {
        val menu = bottomNavigationView.menu
        menu.clear()

        val egoItem = NavigationItem.EGO
        menu.add(ZERO, egoItem.fragmentId, ZERO, "Ego").setIcon(egoItem.iconRes)

        val itemsToAdd = listOf(
            NavigationItem.ADDITION to uiState.isAdditionChecked,
            NavigationItem.SUBTRACTION to uiState.isSubtractionChecked,
            NavigationItem.MULTIPLICATION to uiState.isMultiplicationChecked,
            NavigationItem.DIVISION to uiState.isDivisionChecked,
            NavigationItem.MODULO to uiState.isModuloChecked
        )

        var addedItemCount = ONE
        var toastShown = false
        itemsToAdd.forEachIndexed { index, (item, isChecked) ->
            if (isChecked && addedItemCount < FIVE) {
                menu.add(ZERO, item.fragmentId, index + ONE, item.name).setIcon(item.iconRes)
                addedItemCount++
            } else if (isChecked && !toastShown) {
                Toast.makeText(requireContext(), getString(R.string.limit_five), Toast.LENGTH_SHORT).show()
                toastShown = true
            }
        }
    }

    private fun setSwitchColors(isChecked: Boolean, switch: SwitchMaterial) {
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