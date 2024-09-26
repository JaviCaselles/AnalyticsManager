package com.example.analyticsexample.clients

import com.example.analyticsexample.event.AnalyticsEvent
import com.example.analyticsexample.event.LoginEvent
import com.example.analyticsexample.event.PurchaseEvent

class ZenitAnalyticsClient() : AnalyticsClient {

    override fun logEvent(event: AnalyticsEvent) {
        when (event) {
            is PurchaseEvent -> {
                val purchaseData = ZenitPurchaseData(
                    event.itemId, event.price, event.currency
                )

                // This is the custom Zenit log event
                println("Analytics - Zenit event: $purchaseData")
            }
            is LoginEvent -> {

                // This is the custom Zenit log event
                println("Analytics - Zenit event: ${ZenitLoginData(event.userId)}")
            }
            else -> {

                // This is the custom Zenit log event
                println("Analytics - Zenit event: ${ZenitGenericEventData(event.name, event.parameters)}")
            }
        }
    }

    override fun setUserProperty(propertyName: String, value: String) {
        println("Analytics - Zenit setting user property $propertyName to $value")
    }
}

//Example Event Models
data class ZenitPurchaseData(
    val itemId: String,
    val price: Double,
    val currency: String
)

data class ZenitLoginData(
    val userId: String,
)

data class ZenitGenericEventData(
    val name: String,
    val parameters: Map<String, Any>?
)