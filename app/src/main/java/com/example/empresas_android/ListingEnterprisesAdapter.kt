package com.example.empresas_android

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_enterprise.view.*

class ListingEnterprisesAdapter(
    private val onItemClick:(ItemEnterprise) -> Unit
) : RecyclerView.Adapter<ListingEnterprisesAdapter.ViewHolder>() {
    var contentList: List<ItemEnterprise> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_enterprise, parent, false)
    )

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(contentList[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemEnterprise) {
            itemView.apply {
                titleEnterprise.text = item.name
                subtitleEnterprise.text = item.description
                setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

}

data class ItemEnterprise(
    var name: String,
    var description: String,
    var image: String
)