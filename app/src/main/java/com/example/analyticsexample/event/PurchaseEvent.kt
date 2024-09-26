package com.example.analyticsexample.event

data class PurchaseEvent(
    val itemId: String,
    val price: Double,
    val currency: String
) : AnalyticsEvent {
    override val name: String = "purchase_made"
    override val parameters: Map<String, Any> = mapOf(
        "item_id" to itemId,
        "price" to price,
        "currency" to currency
    )
}

