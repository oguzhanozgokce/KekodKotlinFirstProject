package com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego

import com.oguzhanozgokce.kekodkotlinfirstproject.R

enum class NavigationItem(
    val fragmentId: Int,
    val switchId: Int,
    val iconRes: Int
) {
    EGO(R.id.egoFragment, R.id.switch_ego, R.drawable.ic_stop),
    ADDITION(R.id.additionFragment, R.id.switch_addition, R.drawable.ic_addition),
    SUBTRACTION(R.id.subtractionFragment, R.id.switch_subtraction, R.drawable.ic_remove),
    MULTIPLICATION(R.id.multiplicationFragment, R.id.switch_multiplication, R.drawable.ic_close),
    DIVISION(R.id.divisionFragment, R.id.switch_division, R.drawable.ic_check),
    MODULO(R.id.moduloFragment, R.id.switch_modulo, R.drawable.ic_mode)
}
