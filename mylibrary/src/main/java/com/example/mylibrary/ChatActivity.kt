package com.example.mylibrary

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.databinding.ActivityChatBinding

internal class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var webSocketClient: ChatWebSocket
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.chatRecyclerView
        editText = binding.messageEditText
        adapter = MessageAdapter(messages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        webSocketClient = ChatWebSocket { message, isSent ->
            runOnUiThread {
                messages.add(ChatMessage(message, isSent))
                adapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
            }
        }

        webSocketClient.connect()

        binding.sendButton.setOnClickListener {
            val message = editText.text.toString()
            if (message.isNotBlank()) {
                webSocketClient.sendMessage(message)
                editText.text.clear()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketClient.disconnect()
    }
}
