package com.example.analyticsexample.clients

import com.example.analyticsexample.event.AnalyticsEvent


interface AnalyticsClient {
    fun logEvent(event: AnalyticsEvent)
    fun setUserProperty(propertyName: String, value: String)
}
