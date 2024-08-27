package com.oguzhanozgokce.kekodkotlinfirstproject.ui

import com.google.android.material.switchmaterial.SwitchMaterial

fun List<SwitchMaterial>.setEnabled(isEnabled: Boolean) {
    this.forEach { switch ->
        switch.isEnabled = isEnabled
    }
}

fun List<SwitchMaterial>.resetSwitches() {
    this.forEach { switch ->
        switch.isChecked = false
    }
}