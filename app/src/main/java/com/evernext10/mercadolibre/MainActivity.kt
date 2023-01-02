package com.evernext10.mercadolibre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.evernext10.mercadolibre.databinding.ActivityMainBinding
import com.evernext10.navigation.Destination
import com.evernext10.navigation.setFragmentNavigationListener

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        supportFragmentManager.setFragmentNavigationListener(this) { destination ->
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            when (destination) {
                is Destination.RecipesList -> {
                    navController.navigate(R.id.action_to_recipesList)
                }
                is Destination.RecipesDetail -> {
                    navController.navigate(R.id.action_to_recipesDetail, bundleOf("recipes" to destination.recipes))
                }
                is Destination.RecipesLocation -> {
                    navController.navigate(R.id.action_to_recipesDetailLocation, bundleOf("location" to destination.location, "recipes_name" to destination.recipeName))
                }
                else -> {
                    navController.navigate(R.id.action_to_recipesList)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
