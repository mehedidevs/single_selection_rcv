package com.mehedi.singleselectionrcv

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

data class ColorItem(val colorHex: String)

class ColorSelectionAdapter :
    ListAdapter<ColorItem, ColorSelectionAdapter.ViewHolder>(ColorDiffCallback()) {
    
    private var selectedPosition = RecyclerView.NO_POSITION
    private var onColorSelectedListener: ((String) -> Unit)? = null
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: View = itemView.findViewById(R.id.colorView)
        val checkIcon: ImageView = itemView.findViewById(R.id.checkIcon)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_single_selection, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val colorItem = getItem(position)
        holder.colorView.setBackgroundColor(Color.parseColor(colorItem.colorHex))
        
        holder.checkIcon.visibility = if (position == selectedPosition) View.VISIBLE else View.GONE
        
        if (position == selectedPosition) {
            val color = Color.parseColor(colorItem.colorHex)
            val luminance = ColorUtils.calculateLuminance(color)
            holder.checkIcon.setColorFilter(if (luminance > 0.5) Color.BLACK else Color.WHITE)
        }
        
        holder.itemView.setOnClickListener {
            setSelectedPosition(position)
            onColorSelectedListener?.invoke(colorItem.colorHex)
        }
    }
    
    fun setSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }
    
    fun setOnColorSelectedListener(listener: (String) -> Unit) {
        onColorSelectedListener = listener
    }
}

class ColorDiffCallback : DiffUtil.ItemCallback<ColorItem>() {
    override fun areItemsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
        return oldItem.colorHex == newItem.colorHex
    }
    
    override fun areContentsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
        return oldItem == newItem
    }
}