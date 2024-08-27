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
    }

    private fun resetOtherSwitches() {
        _uiState.value = _uiState.value?.copy(
            isAdditionChecked = false,
            isSubtractionChecked = false,
            isMultiplicationChecked = false,
            isDivisionChecked = false,
            isModuloChecked = false
        )
    }

    private fun setOtherSwitchesEnabled(isEnabled: Boolean) {
        _uiState.value = _uiState.value?.copy(areOtherSwitchesEnabled = isEnabled)
    }
}
