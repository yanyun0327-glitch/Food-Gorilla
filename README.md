FoodGorilla: Delivery Operations & Food Logic System 🦍🥘
FoodGorilla is a high-performance Android application designed to bridge the gap between users and local culinary merchants. Moving beyond simple UI displays, it functions as a state-driven engine for food discovery, menu aggregation, and order lifecycle management.
📝 Functional Overview
The core function of FoodGorilla is to manage the flow of digital commerce—from the initial discovery of a merchant to the finalization of a checkout intent. The system is engineered to handle dynamic data processing, persistent cart logic, and high-volume asset management with low latency.
The Goal: To provide a robust framework that translates user intent into successful food transactions through a reliable, logic-first architecture.
✨ Key Functions
•
Merchant Discovery & Indexing: A logical filtering system that categorizes vendors based on availability and merchant data, allowing for efficient browsing of local food sources.
•
Asynchronous Data Pipeline: Leverages the Coil engine to manage the background fetching and memory-safe caching of high-resolution menu assets without interrupting the system's main thread.
•
State-Persistent Cart Logic: Built using ViewModel and StateFlow to ensure that order selections and user configurations remain persistent across navigation layers.
•
Dynamic Menu Aggregation: An operational layer that organizes complex merchant menus into interactive, selectable data sets for order assembly.
•
Type-Safe Operational Routing: A structured Navigation system that enforces logical flow, ensuring users move through the Discovery → Selection → Checkout pipeline securely.
🌐 Connecting to OpenAPI (REST Services)
To scale FoodGorilla beyond a local prototype, the system is designed to interface with external OpenAPI (REST) specifications. This allows the app to fetch real-time restaurant data, process live payments, and track deliveries.
Recommended Integration Pathways:
1. Contract-First Development (OpenAPI Generator)
You can use the OpenAPI Generator to automatically create Kotlin data models and API interfaces directly from a YAML/JSON specification. This ensures the app's data structures perfectly match the backend.
2. Network Stack Implementation
To connect to live food-service APIs, integrate the following industry-standard stack into your build.gradle.kts:
•
Retrofit: For defining API endpoints as Kotlin interfaces.
•
OkHttp: For handling network intercepts, logging, and security (SSL pinning).
•
Kotlinx Serialization: For parsing JSON responses into the app’s internal models.
3. Handling API Operations
•
GET /restaurants: Fetching the merchant index.
•
GET /menu/{id}: Retrieving live pricing and stock levels.
•
POST /orders: Sending the finalized checkout intent to the server.
🛠 Functional Tech Stack
The application's logic is powered by the modern stack defined in your build.gradle.kts:
•
Core Engine: Kotlin 2.0.0+ (JVM Target 11) for high-speed, type-safe operations.
•
Navigation Controller: androidx.navigation:navigation-compose (v2.8.5) — Manages the functional stages of the user journey.
•
Operational Logic & State: androidx.lifecycle:lifecycle-viewmodel-compose (v2.8.7) — Orchestrates business logic and maintains data integrity.
•
Resource Management: io.coil-kt:coil-compose (v2.7.0) — Handles the background processing of external merchant media.
•
Target Platform: Optimized for Android 15 (API 35) to utilize the latest system-level performance enhancements.
🏗 System Architecture
The project follows a clean, modular structure to separate display from functional logic:
Kotlin
com.example.foodgorilla
├── ui
│   ├── navigation     # Functional routing and operational flow│   ├── screens        # Logic-to-View mapping (Home, Menu, Checkout)
│   └── theme          # System-wide design tokens (Material 3)
├── viewmodel          # The "Brain" — Handles cart logic, math, and data state
├── data               # Data models, Repository logic, and API Service definitions
└── MainActivity.kt    # Entry point and Navigation Host
📋 System Requirements
•
Android Studio: Ladybug | 2024.2.1 or newer
•
Minimum SDK: API 30 (Android 11)
•
Target SDK: API 35 (Android 15)
•
Java Runtime: JDK 11
📦 Installation & Setup
1.
Clone the repository:
Shell Script
git clone https://github.com/your-username/FoodGorilla.git
2.
Import Project: Open the FoodGorilla folder in Android Studio.
3.
Gradle Sync: Allow the system to fetch dependencies (Compose BOM 2024.12.01).
4.
Environment: Ensure you have a valid API Key if connecting to an external OpenAPI service.
5.
Deploy: Run on an emulator or physical device with API 30+.
📄 License
Copyright 2024 FoodGorilla Development Team

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
