package org.eurekamps.dam2_app2.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.dam2_app2.R

class StringViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = itemView.findViewById(R.id.list_cell1_txtview)
    val constrains : ConstraintLayout = itemView.findViewById(R.id.list1_cell_raiz)

}