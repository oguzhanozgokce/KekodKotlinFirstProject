package com.oguzhanozgokce.kekodkotlinfirstproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.EgoUiState
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.EgoViewModel
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

}
