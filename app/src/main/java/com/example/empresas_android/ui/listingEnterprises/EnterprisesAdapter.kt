package com.example.empresas_android.ui.listingEnterprises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.service.model.EnterpriseResponse
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import kotlinx.android.synthetic.main.item_enterprise.view.*

class ListingEnterprisesAdapter(
    private val onItemClick: (EnterpriseResponse) -> Unit
) : RecyclerView.Adapter<ListingEnterprisesAdapter.ViewHolder>() {
    var contentList: List<EnterpriseResponse> = mutableListOf()
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
        fun bind(item: EnterpriseResponse) {
            itemView.apply {
                titleEnterprise.text = item.enterprise_name
                subtitleEnterprise.text = item.city
                subtitleEnterprise2.text = item.country
                val urlImg = URL_IMGS.elementAt(item.description.length % URL_IMGS.size)
                Glide.with(this).load(urlImg).placeholder(R.drawable.img_e_1_lista).into(imageView)
                setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}

//Essa data class esta sendo utilizada ?
data class ItemEnterprise(
    var name: String,
    var area: String,
    var country: String,
    var description: String,
    var image: String
)

//dar nome significativo para img_e_1 e img_e_1_lista