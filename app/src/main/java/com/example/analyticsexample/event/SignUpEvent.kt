package com.example.analyticsexample.event

data class SignUpEvent(
    val userId: String,
    val success: Boolean
) : AnalyticsEvent {
    override val name: String = "sign_up"
    override val parameters: Map<String, Any> = mapOf(
        "method" to userId,
        "success" to success
    )
}
