package com.ryanluo.prefloppal.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.data.TermItem

class TermAdapter(private val termItems: List<TermItem>) :
    RecyclerView.Adapter<TermAdapter.TermViewHolder>() {

    class TermViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val termText: TextView = itemView.findViewById(R.id.termText)
        val definitionText: TextView = itemView.findViewById(R.id.definitionText)
        val expandIcon: ImageView = itemView.findViewById(R.id.expandIcon)
        val termLayout: View = itemView.findViewById(R.id.termLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.terminology_item, parent, false)
        return TermViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        val item = termItems[position]

        holder.termText.text = item.term
        holder.definitionText.text = Html.fromHtml(item.definition, Html.FROM_HTML_MODE_COMPACT)

        // Set initial state
        holder.definitionText.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
        holder.expandIcon.rotation = if (item.isExpanded) 180f else 0f

        holder.termLayout.setOnClickListener {
            // Toggle expansion
            item.isExpanded = !item.isExpanded

            // Animate icon rotation
            holder.expandIcon.animate()
                .rotation(if (item.isExpanded) 180f else 0f)
                .setDuration(200)
                .start()

            // Show/hide definition with animation
            if (item.isExpanded) {
                holder.definitionText.visibility = View.VISIBLE
                holder.definitionText.alpha = 0f
                holder.definitionText.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
            } else {
                holder.definitionText.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction {
                        holder.definitionText.visibility = View.GONE
                    }
                    .start()
            }
        }
    }


    override fun getItemCount(): Int = termItems.size


}
