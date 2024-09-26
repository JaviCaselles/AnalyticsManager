package com.example.analyticsexample.event

sealed interface AnalyticsEvent {
    val name: String
    val parameters: Map<String, Any>?
}
