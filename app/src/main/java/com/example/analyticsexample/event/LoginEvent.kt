package com.example.analyticsexample.event

data class LoginEvent(
    val userId: String
) : AnalyticsEvent {
    override val name: String = "login"
    override val parameters: Map<String, Any> = mapOf(
        "user-id" to userId
    )
}
