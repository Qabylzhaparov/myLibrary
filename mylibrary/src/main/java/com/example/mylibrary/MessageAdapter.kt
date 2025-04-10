package com.example.mylibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class SentMessageViewHolder(view: View) : RecyclerView.ViewHolder(view)
internal class ReceivedMessageViewHolder(view: View) : RecyclerView.ViewHolder(view)

internal data class ChatMessage(val text: String, val isSent: Boolean)

internal class MessageAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) =
        if (messages[position].isSent) 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {
            val view = inflater.inflate(R.layout.item_message_sent, parent, false)
            SentMessageViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_message_received, parent, false)
            ReceivedMessageViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        val textView = holder.itemView.findViewById<TextView>(R.id.messageTextView)
        textView.text = message.text
    }


    override fun getItemCount() = messages.size

}