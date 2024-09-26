package com.example.analyticsexample.manager

import android.content.Context
import com.example.analyticsexample.clients.AnalyticsClient
import com.example.analyticsexample.clients.FirebaseAnalyticsClient
import com.example.analyticsexample.clients.ZenitAnalyticsClient
import com.example.analyticsexample.event.AnalyticsEvent

class AnalyticsManager() {

    private val clients: List<AnalyticsClient>

    init {
        val firebaseAnalyticsClient = FirebaseAnalyticsClient()
        val customAnalyticsClient = ZenitAnalyticsClient()

        clients = listOf(
            firebaseAnalyticsClient,
            customAnalyticsClient
        )
    }

    fun logEvent(event: AnalyticsEvent) {
        clients.forEach { client ->
            client.logEvent(event)
        }
    }

    fun setUserProperty(propertyName: String, value: String) {
        clients.forEach { client ->
            client.setUserProperty(propertyName, value)
        }
    }
}
