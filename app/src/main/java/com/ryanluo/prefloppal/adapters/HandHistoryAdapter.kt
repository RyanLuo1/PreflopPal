package com.ryanluo.prefloppal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.data.HandRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HandHistoryAdapter(
    private val handRecords: List<HandRecord>,
    private val onItemClick: (HandRecord) -> Unit
) : RecyclerView.Adapter<HandHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val handText: TextView = view.findViewById(R.id.handText)
        val dateText: TextView = view.findViewById(R.id.dateText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hand_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = handRecords[position]
        holder.apply {
            handText.text = "${record.card1} ${record.card2}"
            dateText.text = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())
                .format(Date(record.timestamp))

            itemView.setOnClickListener {
                onItemClick(record)
            }
        }
    }

    override fun getItemCount() = handRecords.size
}