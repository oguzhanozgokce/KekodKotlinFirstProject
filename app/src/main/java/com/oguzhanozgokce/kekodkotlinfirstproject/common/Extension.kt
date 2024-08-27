package com.oguzhanozgokce.kekodkotlinfirstproject.common

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
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

fun BottomNavigationView.gone() {
    this.visibility = View.GONE
}

fun BottomNavigationView.visible() {
    this.visibility = View.VISIBLE
}
