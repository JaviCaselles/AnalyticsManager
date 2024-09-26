package com.example.analyticsexample.clients

import com.example.analyticsexample.event.AnalyticsEvent
import com.example.analyticsexample.event.LoginEvent
import com.example.analyticsexample.event.PurchaseEvent

class ZenitAnalyticsClient() : AnalyticsClient {

    override fun logEvent(event: AnalyticsEvent) {
        try {
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
                    println(
                        "Analytics - Zenit event: ${
                            ZenitGenericEventData(
                                event.name,
                                event.parameters
                            )
                        }"
                    )
                }
            }
        } catch (e: Exception) {
            // Propagate the exception to AnalyticsManager or handle it here
            throw e
        }
    }

    override fun setUserProperty(propertyName: String, value: String) {
        try {
            // This is the custom Zenit set user property
            println("Analytics - Zenit setting user property $propertyName to $value")
        } catch (e: Exception) {
            // Propagate the exception to AnalyticsManager or handle it here
            throw e
        }
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