package com.example.empresas_android.ui.listingEnterprises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.domain.entities.EnterpriseEntity
import kotlinx.android.synthetic.main.item_enterprise.view.*

class ListingEnterprisesAdapter(
    private val onItemClick: (EnterpriseEntity) -> Unit
) : RecyclerView.Adapter<ListingEnterprisesAdapter.ViewHolder>() {
    var contentList: List<EnterpriseEntity> = mutableListOf()
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
        fun bind(item: EnterpriseEntity) {
            itemView.apply {
                titleEnterprise.text = item.enterprise_name
                itemEnterpriseCityTextView.text = item.city
                itemEnterpriseCountryTextView.text = item.country
                val urlImg = URL_IMGS.elementAt(item.description.length % URL_IMGS.size)
                Glide.with(this).load(urlImg).placeholder(R.drawable.img_e_1_lista).into(itemEnterprisePhotoImageView)
                setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}
