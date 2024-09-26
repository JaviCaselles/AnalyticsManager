package com.example.analyticsexample.clients


import android.os.Bundle
import com.example.analyticsexample.event.AnalyticsEvent

class FirebaseAnalyticsClient() : AnalyticsClient {
    override fun logEvent(event: AnalyticsEvent) {
        try {
            val bundle = Bundle().apply {
                event.parameters?.forEach { (key, value) ->
                    when (value) {
                        is String -> putString(key, value)
                        is Int -> putInt(key, value)
                        is Double -> putDouble(key, value)
                        is Boolean -> putBoolean(key, value)
                        else -> putString(key, value.toString())
                    }
                }
            }

            // This is the custom Firebase log event
            println("Analytics - Firebase event: ${event.name} with parameters: $bundle")
        } catch (e: Exception) {
            // Propagate the exception to AnalyticsManager or handle it here
            throw e
        }
    }

    override fun setUserProperty(propertyName: String, value: String) {
        try {
            // This is the custom Firebase set user property
            println("Analytics - Firebase setting user property $propertyName to $value")
        } catch (e: Exception) {
            // Propagate the exception to AnalyticsManager or handle it here
            throw e
        }
    }
}

