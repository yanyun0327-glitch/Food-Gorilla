package com.example.foodgorilla.data

object Repository {
    val restaurants = listOf(
        Restaurant(
            "1", 
            "MidOnald's", 
            "I'm lovin' it (fake)", 
            4.5, 
            "https://images.unsplash.com/photo-1552566626-52f8b828add9?q=80&w=500&auto=format&fit=crop"
        ),
        Restaurant(
            "2", 
            "Burger Kingpin", 
            "Be the king of burgers", 
            4.2, 
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add?q=80&w=500&auto=format&fit=crop"
        ),
        Restaurant(
            "3", 
            "Pizza Hutlet", 
            "No one out-huts the hutlet", 
            4.0, 
            "https://images.unsplash.com/photo-1513104890138-7c749659a591?q=80&w=500&auto=format&fit=crop"
        ),
        Restaurant(
            "4", 
            "Sub-Way-Better", 
            "Eat fresh-ish", 
            4.3, 
            "https://images.unsplash.com/photo-1509722747041-619f382b73b0?q=80&w=500&auto=format&fit=crop"
        ),
        Restaurant(
            "5", 
            "KFC-Ollie", 
            "Finger lickin' good-ish", 
            4.1, 
            "https://images.unsplash.com/photo-1513639776629-7b61b0ac49cb?q=80&w=500&auto=format&fit=crop"
        )
    )

    val menuItems = listOf(
        MenuItem("101", "1", "Spicy Chicken Burger", "Crispy spicy chicken with lettuce", 5.99, "https://images.unsplash.com/photo-1610440042657-612c34d95e9f?q=80&w=300&auto=format&fit=crop"),
        MenuItem("102", "1", "Quarter Pounder Clone", "Beef patty with cheese and pickles", 6.49, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=300&auto=format&fit=crop"),
        MenuItem("103", "1", "Large Fries", "Golden salty potato sticks", 2.99, "https://images.unsplash.com/photo-1630384066255-447723232c24?q=80&w=300&auto=format&fit=crop"),
        MenuItem("201", "2", "Whopper Jr.", "The classic burger", 5.49, "https://images.unsplash.com/photo-1550547660-d9450f859349?q=80&w=300&auto=format&fit=crop"),
        MenuItem("202", "2", "Onion Rings", "Golden fried onion rings", 3.49, "https://images.unsplash.com/photo-1639024471283-03518883512d?q=80&w=300&auto=format&fit=crop"),
        MenuItem("301", "3", "Pepperoni Pizza", "Classic pepperoni and cheese", 12.99, "https://images.unsplash.com/photo-1628840042765-356cda07504e?q=80&w=300&auto=format&fit=crop"),
        MenuItem("302", "3", "Garlic Bread", "Buttery garlic bread", 4.99, "https://images.unsplash.com/photo-1573140247632-f8fd74997d5c?q=80&w=300&auto=format&fit=crop")
    )

    fun getRestaurant(id: String) = restaurants.find { it.id == id }
    fun getMenuItems(restaurantId: String) = menuItems.filter { it.restaurantId == restaurantId }
}
