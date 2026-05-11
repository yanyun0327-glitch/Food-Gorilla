package com.example.foodgorilla

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodgorilla.data.*

class FoodViewModel : ViewModel() {
    var restaurants = mutableStateOf(Repository.restaurants)
    var cartItems = mutableStateListOf<CartItem>()
    var deliveryAddress = mutableStateOf(Address("123 Demo St", "Tech City", "12345"))
    var selectedPaymentMethod = mutableStateOf(PaymentMethod.CASH)

    val deliveryFee = 2.0
    val platformFee = 0.5

    fun searchRestaurants(query: String) {
        restaurants.value = Repository.restaurants.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun addToCart(menuItem: MenuItem, quantity: Int) {
        val existingItem = cartItems.find { it.menuItem.id == menuItem.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            cartItems.add(CartItem(menuItem, quantity))
        }
    }

    fun removeFromCart(menuItem: MenuItem) {
        cartItems.removeIf { it.menuItem.id == menuItem.id }
    }

    fun getCartSubtotal(): Double {
        return cartItems.sumOf { it.menuItem.price * it.quantity }
    }

    fun getCartTotal(): Double {
        val subtotal = getCartSubtotal()
        return if (subtotal > 0) subtotal + deliveryFee + platformFee else 0.0
    }

    fun clearCart() {
        cartItems.clear()
    }
}
