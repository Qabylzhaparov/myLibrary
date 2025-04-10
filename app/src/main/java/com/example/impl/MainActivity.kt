package com.example.impl

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.ChatLibrary

class MainActivity : AppCompatActivity() {

    private val chatLibrary = ChatLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        chatLibrary.start(this)
    }

}