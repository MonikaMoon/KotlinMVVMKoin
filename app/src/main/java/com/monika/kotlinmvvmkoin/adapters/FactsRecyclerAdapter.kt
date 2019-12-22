package com.monika.kotlinmvvmkoin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monika.kotlinmvvmkoin.R
import com.monika.kotlinmvvmkoin.model.Facts
import kotlinx.android.synthetic.main.list_item_layout.view.*

class FactsRecyclerAdapter(var context: Context, var factsList: ArrayList<Facts>) : RecyclerView.Adapter<FactsRecyclerAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (factsList.get(position).title != null) {

            holder.itemView.visibility = View.VISIBLE
            holder.itemView.layoutParams =
                RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            holder.textViewTitle.text = factsList.get(position).title
            holder.textViewDescription.text = factsList.get(position).description
            Glide.with(context)
                .load(factsList.get(position).imageHref)
                .into(holder.imageView)
        } else {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return factsList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle = itemView.findViewById(R.id.textview_title) as TextView
        val textViewDescription  = itemView.findViewById(R.id.textview_description) as TextView
        val imageView  = itemView.image_view!!
    }
}