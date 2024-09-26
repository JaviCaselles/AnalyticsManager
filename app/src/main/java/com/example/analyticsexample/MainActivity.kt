package com.example.analyticsexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.analyticsexample.event.LoginEvent
import com.example.analyticsexample.event.PurchaseEvent
import com.example.analyticsexample.event.SignUpEvent
import com.example.analyticsexample.manager.AnalyticsManager

class MainActivity : AppCompatActivity() {

    private lateinit var analyticsManager: AnalyticsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        analyticsManager = AnalyticsManager()

        analyticsManager.logEvent(PurchaseEvent(itemId = "123", price = 10.0, currency = "USD"))
        analyticsManager.logEvent(LoginEvent(userId = "Javi"))
        analyticsManager.logEvent(SignUpEvent(userId = "Javi", success = true))

        analyticsManager.setUserProperty("username", "Javi")
    }
}