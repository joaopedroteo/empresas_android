package com.example.empresas_android.ui.listingEnterprises

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.service.model.EnterpriseResponse
import com.example.empresas_android.databinding.ItemEnterpriseBinding
import com.example.empresas_android.presentation.ItemEnterpriseViewModel
import kotlinx.android.synthetic.main.item_enterprise.view.*

class ListingEnterprisesAdapter(
    private val onItemClick: (EnterpriseResponse) -> Unit
) : RecyclerView.Adapter<ListingEnterprisesAdapter.ViewHolder>() {

    lateinit var binding:ItemEnterpriseBinding

    var contentList: MutableList<EnterpriseResponse> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        binding = ItemEnterpriseBinding.inflate(layoutInflater, parent, false)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_enterprise, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = contentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

//    override fun onBindViewHolder(@NonNull enterpriseViewModel: ItemEnterpriseViewModel, position: Int) {
//        enterpriseViewModel(contentList[position])
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("DEBUG", position.toString())
        binding.viewModel = ItemEnterpriseViewModel(contentList[position])
        Log.d("DEBUG", contentList[position].enterprise_name)
        holder.bind(contentList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         fun bind(item: EnterpriseResponse) {

            itemView.apply {

                val urlImg = URL_IMGS.elementAt(item.description.length % URL_IMGS.size)
                Glide.with(this).load(urlImg).placeholder(R.drawable.img_e_1_lista).into(imageView)
                setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}
