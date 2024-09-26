package com.example.analyticsexample.manager

import android.util.Log
import com.example.analyticsexample.clients.AnalyticsClient
import com.example.analyticsexample.clients.FirebaseAnalyticsClient
import com.example.analyticsexample.clients.ZenitAnalyticsClient
import com.example.analyticsexample.event.AnalyticsEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class AnalyticsManager(
    analyticsClients: List<AnalyticsClient>? = null
) {

    private val clients: List<AnalyticsClient>

    private val eventChannel = Channel<AnalyticsEvent>(Channel.UNLIMITED)
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        clients = analyticsClients ?: run {
            val firebaseAnalyticsClient = FirebaseAnalyticsClient()
            val zenitAnalyticsClient = ZenitAnalyticsClient()

            listOf(
                firebaseAnalyticsClient,
                zenitAnalyticsClient
            )
        }

        coroutineScope.launch {
            processEvents()
        }
    }

    fun logEvent(event: AnalyticsEvent) {
        coroutineScope.launch {
            eventChannel.send(event)
        }
    }

    fun setUserProperty(propertyName: String, value: String) {
        coroutineScope.launch {
            clients.forEach { client ->
                try {
                    client.setUserProperty(propertyName, value)
                } catch (e: Exception) {
                    handleUserPropertyException(propertyName, value, e)
                }
            }
        }
    }

    // We can add a retry mechanism here if needed
    private suspend fun processEvents() {
        for (event in eventChannel) {
            clients.forEach { client ->
                try {
                    client.logEvent(event)
                } catch (e: Exception) {
                    handleEventException(event, e)
                }
            }
        }
    }

    private fun handleEventException(event: AnalyticsEvent, exception: Exception) {
        Log.e("AnalyticsManager", "Error processing event: ${event.name}", exception)
    }

    private fun handleUserPropertyException(propertyName: String, value: String, exception: Exception) {
        Log.e("AnalyticsManager", "Error setting user property: $propertyName", exception)
    }

    private fun clear() {
        coroutineScope.cancel()
    }
}
