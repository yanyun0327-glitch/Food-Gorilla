package com.example.foodgorilla.data

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val rating: Double,
    val imageUrl: String = ""
)

data class MenuItem(
    val id: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String = ""
)

data class CartItem(
    val menuItem: MenuItem,
    var quantity: Int
)

data class Address(
    val street: String = "",
    val city: String = "",
    val postalCode: String = ""
)

enum class PaymentMethod(val displayName: String) {
    CASH("Cash on Delivery"),
    CREDIT_CARD("Credit Card"),
    E_WALLET("E-Wallet")
}
