package com.cengiztoru.architecturalcomponentssample.ui.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cengiztoru.architecturalcomponentssample.R
import com.cengiztoru.architecturalcomponentssample.data.network.responses.QuotesResponse
import com.cengiztoru.architecturalcomponentssample.databinding.ItemQuotesBinding

class QuotesAdapter(private val list: List<QuotesResponse.Quote>) :
    RecyclerView.Adapter<QuotesAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_quotes,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.mBinding.quote = list[position]

        //CLICK LISTENERS
    }

    class CustomViewHolder(val mBinding: ItemQuotesBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun getItemCount() = list.size

}