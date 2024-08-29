package com.oguzhanozgokce.kekodkotlinfirstproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.EgoUiState
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.EgoViewModel
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.NavigationItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EgoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EgoViewModel

    @Before
    fun setup() {
        viewModel = EgoViewModel()
    }
    // Ego switch'i açıldığında, diğer switch'ler devre dışı bırakılmal
    @Test
    fun `when Ego switch is checked, other switches should be disabled`() {
        viewModel.onEgoSwitchChanged(true)
        assertEquals(EgoUiState(isEgoChecked = true), viewModel.uiState.value)
    }
    @Test
    fun `when Ego switch is not checked, other switches should be disabled`() {
        viewModel.onEgoSwitchChanged(false)
        assertEquals(EgoUiState(areOtherSwitchesEnabled = true, isEgoChecked = false), viewModel.uiState.value)
    }
    // Addition switch'i açıldığında, UI durumu güncellenmeli ve addedItemsOrder listesine eklenmeli
    @Test
    fun `when Addition switch is checked, UI state should be updated`() {
        viewModel.onOtherSwitchChanged(R.id.switch_addition, true)
        assertEquals(true, viewModel.uiState.value?.isAdditionChecked)
        assertTrue(viewModel.addedItemsOrder.value?.contains(NavigationItem.ADDITION) == true)
    }
    @Test
    fun `when Subtraction switch is checked, UI state should be updated`() {
        viewModel.onOtherSwitchChanged(R.id.switch_subtraction, true)
        assertEquals(true, viewModel.uiState.value?.isSubtractionChecked)
        assertTrue(viewModel.addedItemsOrder.value?.contains(NavigationItem.SUBTRACTION) == true)
    }
    @Test
    fun `when Multiplication switch is checked, UI state should be updated`() {
        viewModel.onOtherSwitchChanged(R.id.switch_multiplication, true)
        assertEquals(true, viewModel.uiState.value?.isMultiplicationChecked)
        assertTrue(viewModel.addedItemsOrder.value?.contains(NavigationItem.MULTIPLICATION) == true)
    }
    @Test
    fun `when Division switch is checked, UI state should be updated`() {
        viewModel.onOtherSwitchChanged(R.id.switch_division, true)
        assertEquals(true, viewModel.uiState.value?.isDivisionChecked)
        assertTrue(viewModel.addedItemsOrder.value?.contains(NavigationItem.DIVISION) == true)
    }
    @Test
    fun `when Modulo switch is checked, UI state should be updated`() {
        viewModel.onOtherSwitchChanged(R.id.switch_modulo, true)
        assertEquals(true, viewModel.uiState.value?.isModuloChecked)
        assertTrue(viewModel.addedItemsOrder.value?.contains(NavigationItem.MODULO) == true)
    }
    @Test
    fun `when Addition switch is unchecked, it should be removed from addedItemsOrder`() {
        viewModel.onOtherSwitchChanged(R.id.switch_addition, true)
        viewModel.onOtherSwitchChanged(R.id.switch_addition, false)
        assertEquals(false, viewModel.uiState.value?.isAdditionChecked)
        assertFalse(viewModel.addedItemsOrder.value?.contains(NavigationItem.ADDITION) == true)
    }
    // Geçersiz switchId verildiğinde, UI durumu değişmemeli ve addedItemsOrder listesi boş kalmalı
    @Test
    fun `when an invalid switchId is provided, UI state should not change`() {
        val previousUiState = viewModel.uiState.value
        val invalidSwitchId = -1
        viewModel.onOtherSwitchChanged(invalidSwitchId, true)
        assertEquals(previousUiState, viewModel.uiState.value)
        assertTrue(viewModel.addedItemsOrder.value?.isEmpty() == true)
    }
}
