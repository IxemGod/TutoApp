package com.example.tutoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PostAdapter (
    var mContext : Context,
    var resource: Int,
    var values: ArrayList<String>
): ArrayAdapter<String>(mContext, resource, values){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val post = values[position]
        val itemView = LayoutInflater.from(mContext).inflate(resource, parent, false)

        val tvTitre = itemView.findViewById<TextView>(R.id.titre)
        tvTitre.text = post
        return itemView
    }
}