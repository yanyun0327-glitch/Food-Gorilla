package com.example.foodgorilla

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.foodgorilla.ui.*
import com.example.foodgorilla.ui.theme.FoodGorillaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodGorillaTheme {
                FoodApp(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}

@Composable
fun FoodApp(intent: Intent? = null) {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = viewModel()
    val uriPrefix = "foodgorilla://app"

    // Public API Logic: Handle "Add to Cart" directly via custom URI scheme
    LaunchedEffect(intent) {
        intent?.data?.let { uri ->
            if (uri.toString().startsWith("foodgorilla://api/add_to_cart")) {
                val itemId = uri.getQueryParameter("itemId")
                val quantity = uri.getQueryParameter("quantity")?.toIntOrNull() ?: 1
                com.example.foodgorilla.data.Repository.menuItems.find { it.id == itemId }?.let {
                    viewModel.addToCart(it, quantity)
                    navController.navigate("cart")
                }
            }
        }
    }

    // Screens now manage their own Scaffolds for better transition and TopBar control
    NavHost(
        navController = navController,
        startDestination = "restaurants",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(
            route = "restaurants?search={search}",
            arguments = listOf(
                navArgument("search") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = "$uriPrefix/restaurants?search={search}" })
        ) { backStackEntry ->
            val search = backStackEntry.arguments?.getString("search") ?: ""
            LaunchedEffect(search) {
                if (search.isNotEmpty()) {
                    viewModel.searchRestaurants(search)
                }
            }
            RestaurantListScreen(
                viewModel = viewModel,
                onRestaurantClick = { id -> navController.navigate("restaurant/$id") },
                onCartClick = { navController.navigate("cart") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable(
            route = "restaurant/{restaurantId}?search={search}",
            arguments = listOf(
                navArgument("restaurantId") { type = NavType.StringType },
                navArgument("search") { 
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = "$uriPrefix/restaurant/{restaurantId}?search={search}" })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
            val search = backStackEntry.arguments?.getString("search") ?: ""
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                initialSearch = search,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate("cart") }
            )
        }

        composable(
            route = "cart",
            deepLinks = listOf(navDeepLink { uriPattern = "$uriPrefix/cart" })
        ) {
            CartScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate("checkout") }
            )
        }

        composable(
            route = "checkout",
            deepLinks = listOf(navDeepLink { uriPattern = "$uriPrefix/checkout" })
        ) {
            CheckoutScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onConfirm = {
                    viewModel.clearCart()
                    navController.navigate("restaurants") {
                        popUpTo("restaurants") { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "settings",
            deepLinks = listOf(navDeepLink { uriPattern = "$uriPrefix/settings" })
        ) {
            SettingsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
