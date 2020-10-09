package com.example.SupercCatApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.SupercCatApp.R
import com.example.SupercCatApp.R.layout.prototype_cat
import com.example.SupercCatApp.models.Cat
import com.squareup.picasso.Picasso

class CatAdapter (private val cats: List<Cat>, private val context: Context, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvTitle = view.findViewById(R.id.tvTitle) as TextView
        val tvOverview = view.findViewById(R.id.tvOverview) as TextView
        val cvCat = view.findViewById(R.id.cvCat) as CardView
        val ivImageCat = view.findViewById(R.id.ivImageCat) as ImageView
    }

    interface OnItemClickListener {
        fun onItemClicked(cat: Cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(prototype_cat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cats.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat  = cats[position]
        holder.tvTitle.text = cat.height
        holder.tvOverview.text = cat.width

        Picasso.get().load(cat.url).placeholder(R.drawable.ic_baseline_autorenew_24).into(holder.ivImageCat)

        holder.cvCat.setOnClickListener {
            itemClickListener.onItemClicked(cat)
        }
    }
}