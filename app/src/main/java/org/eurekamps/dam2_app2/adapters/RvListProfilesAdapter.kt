package org.eurekamps.dam2_app2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.viewholders.StringViewHolder

class RvListProfilesAdapter(val listaDeNombre:List<String>) : RecyclerView.Adapter<StringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cell1, parent, false)
        val stringViewHolder = StringViewHolder(view)
        return stringViewHolder
    }

    override fun getItemCount(): Int {
        return listaDeNombre.size
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textView.text= listaDeNombre[position]
        //holder.constrains.layoutParams.height= 50 * (1+position)
        if(position%3==0){
            holder.constrains.setBackgroundColor(Color.BLUE)
        }
        else if(position%2==0){
            holder.constrains.setBackgroundColor(Color.RED)
        }
        else{
            holder.constrains.setBackgroundColor(Color.YELLOW)
        }


    }

}