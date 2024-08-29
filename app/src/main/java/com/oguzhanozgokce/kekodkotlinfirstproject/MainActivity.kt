package com.oguzhanozgokce.kekodkotlinfirstproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.oguzhanozgokce.kekodkotlinfirstproject.common.gone
import com.oguzhanozgokce.kekodkotlinfirstproject.common.visible
import com.oguzhanozgokce.kekodkotlinfirstproject.databinding.ActivityMainBinding
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.EgoViewModel
import com.oguzhanozgokce.kekodkotlinfirstproject.ui.ego.NavigationItem

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
     val viewModel: EgoViewModel by viewModels()

    companion object {
        const val ZERO = 0
        const val ONE = 1
        const val FIVE = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        viewModel.addedItemsOrder.observe(this) { addedItems ->
            updateBottomNavigationView(addedItems)
            Log.e("addedItems", addedItems.toString())
        }
        viewModel.uiState.observe(this) { uiState ->
            toggleBottomNavVisibility(uiState.isEgoChecked)
        }
    }

    private fun updateBottomNavigationView(addedItemsOrder: List<NavigationItem>) {
        val menu = binding.bottomNavigationView.menu
        menu.clear()

        val egoItem = NavigationItem.EGO
        menu.add(ZERO, egoItem.fragmentId, ZERO, "Ego").setIcon(egoItem.iconRes)

        var addedItemCount = ONE
        var toastShown = false

        addedItemsOrder.forEach { item ->
            if (addedItemCount < FIVE) {
                menu.add(ZERO, item.fragmentId, addedItemCount, item.name).setIcon(item.iconRes)
                addedItemCount++
            } else if (!toastShown) {
                Toast.makeText(this, getString(R.string.limit_five), Toast.LENGTH_SHORT).show()
                toastShown = true
            }
        }
    }

    fun toggleBottomNavVisibility(isEgoChecked: Boolean) {
        if (isEgoChecked) {
            binding.bottomNavigationView.gone()
        } else {
            binding.bottomNavigationView.visible()
        }
    }
}