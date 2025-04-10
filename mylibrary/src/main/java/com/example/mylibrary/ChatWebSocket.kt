package com.example.mylibrary

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

internal class ChatWebSocket (private val onMessageReceived: (String, Boolean) -> Unit) {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    fun connect() {
        val request = Request.Builder()
            .url("wss://echo.websocket.org")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                val messageToShow = if (text == "203 = 0xcb") {
                    "Predefined server message received"
                } else text

                onMessageReceived(messageToShow, false)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onMessageReceived("Connection failed: ${t.message}", false)
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
        onMessageReceived(message, true)
    }

    fun disconnect() {
        webSocket?.close(1000, "Activity destroyed")
        webSocket = null
    }
}