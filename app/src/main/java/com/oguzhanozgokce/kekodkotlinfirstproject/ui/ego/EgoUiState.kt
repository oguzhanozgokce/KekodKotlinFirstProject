package com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.kekodkotlinfirstproject.R

data class EgoUiState(
    val isEgoChecked: Boolean = true,
    val isAdditionChecked: Boolean = false,
    val isSubtractionChecked: Boolean = false,
    val isMultiplicationChecked: Boolean = false,
    val isDivisionChecked: Boolean = false,
    val isModuloChecked: Boolean = false,
    val areOtherSwitchesEnabled: Boolean = false
)

class EgoViewModel : ViewModel() {

    private val _uiState = MutableLiveData(EgoUiState())
    val uiState: LiveData<EgoUiState> = _uiState

    private val _addedItemsOrder = MutableLiveData<List<NavigationItem>>(emptyList())
    val addedItemsOrder: LiveData<List<NavigationItem>> = _addedItemsOrder

    fun onEgoSwitchChanged(isChecked: Boolean) {
        val newState = _uiState.value?.copy(isEgoChecked = isChecked)
        _uiState.value = newState

        if (isChecked) {
            resetOtherSwitches()
            setOtherSwitchesEnabled(false)
        } else {
            setOtherSwitchesEnabled(true)
        }
    }

    fun onOtherSwitchChanged(switchId: Int, isChecked: Boolean) {
        val newState = when (switchId) {
            R.id.switch_addition -> _uiState.value?.copy(isAdditionChecked = isChecked)
            R.id.switch_subtraction -> _uiState.value?.copy(isSubtractionChecked = isChecked)
            R.id.switch_multiplication -> _uiState.value?.copy(isMultiplicationChecked = isChecked)
            R.id.switch_division -> _uiState.value?.copy(isDivisionChecked = isChecked)
            R.id.switch_modulo -> _uiState.value?.copy(isModuloChecked = isChecked)
            else -> _uiState.value
        }
        _uiState.value = newState
        updateAddedItemsOrder(switchId, isChecked)
    }

    private fun resetOtherSwitches() {
        _uiState.value = _uiState.value?.copy(
            isAdditionChecked = false,
            isSubtractionChecked = false,
            isMultiplicationChecked = false,
            isDivisionChecked = false,
            isModuloChecked = false
        )
        _addedItemsOrder.value = emptyList()
    }

    private fun setOtherSwitchesEnabled(isEnabled: Boolean) {
        _uiState.value = _uiState.value?.copy(areOtherSwitchesEnabled = isEnabled)
    }

    private fun updateAddedItemsOrder(switchId: Int, isChecked: Boolean) {
        val currentOrder = _addedItemsOrder.value?.toMutableList() ?: mutableListOf()
        val item = when (switchId) {
            R.id.switch_addition -> NavigationItem.ADDITION
            R.id.switch_subtraction -> NavigationItem.SUBTRACTION
            R.id.switch_multiplication -> NavigationItem.MULTIPLICATION
            R.id.switch_division -> NavigationItem.DIVISION
            R.id.switch_modulo -> NavigationItem.MODULO
            else -> null
        }

        if (item != null) {
            if (isChecked) {
                if (!currentOrder.contains(item)) {
                    currentOrder.add(item)
                }
            } else {
                currentOrder.remove(item)
            }
            _addedItemsOrder.value = currentOrder
        }
    }
}
