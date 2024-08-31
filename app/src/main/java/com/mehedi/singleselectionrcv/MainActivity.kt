package com.mehedi.singleselectionrcv

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ColorSelectionAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
            
        }
        
        adapter = ColorSelectionAdapter()
        recyclerView.adapter = adapter
        
        val colors = listOf(
            ColorItem("#FF0000"), // Red
            ColorItem("#00FF00"), // Green
            ColorItem("#0000FF"), // Blue
            ColorItem("#FFFF00"), // Yellow
            ColorItem("#800080"), // Purple
            ColorItem("#008080"), // Teal
            ColorItem("#FFA500"), // Orange
            ColorItem("#FFC0CB")  // Pink
        )
        
        adapter.submitList(colors)
        
        adapter.setOnColorSelectedListener { colorHex ->
            // Handle color selection
          //  findViewById<View>(R.id.colorView).setBackgroundColor(Color.parseColor(colorHex))
            Toast.makeText(this, "Selected color: $colorHex", Toast.LENGTH_SHORT).show()
        }
    }
}