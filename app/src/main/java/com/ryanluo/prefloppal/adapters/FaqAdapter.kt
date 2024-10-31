package com.ryanluo.prefloppal.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.data.FaqItem

class FaqAdapter(private val faqItems: List<FaqItem>) :
    RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val answerText: TextView = itemView.findViewById(R.id.answerText)
        val expandIcon: ImageView = itemView.findViewById(R.id.expandIcon)
        val questionLayout: View = itemView.findViewById(R.id.questionLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.faq_item, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val item = faqItems[position]

        holder.questionText.text = item.question
        holder.answerText.text = Html.fromHtml(item.answer, Html.FROM_HTML_MODE_COMPACT)  // Add this line

        // Set initial state
        holder.answerText.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
        holder.expandIcon.rotation = if (item.isExpanded) 180f else 0f

        holder.questionLayout.setOnClickListener {
            // Toggle expansion
            item.isExpanded = !item.isExpanded

            // Animate icon rotation
            holder.expandIcon.animate()
                .rotation(if (item.isExpanded) 180f else 0f)
                .setDuration(200)
                .start()

            // Show/hide answer with animation
            if (item.isExpanded) {
                holder.answerText.visibility = View.VISIBLE
                holder.answerText.alpha = 0f
                holder.answerText.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
            } else {
                holder.answerText.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction {
                        holder.answerText.visibility = View.GONE
                    }
                    .start()
            }
        }
    }

    override fun getItemCount() = faqItems.size
}