package com.evernext10.marketplace.product.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evernext10.core.R
import com.evernext10.core.domain.model.recipes.Recipes
import com.evernext10.core.ext.bindImageUrl
import com.evernext10.core.ext.toFormattedNumber
import com.evernext10.marketplace.product.list.presentation.databinding.ItemProductBinding

class RecipesListAdapter(
    private val onClick: (Recipes) -> Unit
) : ListAdapter<Recipes, RecipesListAdapter.ProductViewHolder>(ProductDiffUtil()), Filterable {

    private var completeItemList : MutableList<Recipes>? = mutableListOf()

    companion object {
        private class ProductDiffUtil : DiffUtil.ItemCallback<Recipes>() {
            override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val viewItem: ItemProductBinding, val onClick: (Recipes) -> Unit) :
        RecyclerView.ViewHolder(viewItem.root) {
        fun bind(product: Recipes) {
            product.let {
                with(viewItem) {
                    productName.text = it.name
                    averageCost.text = it.average_cost?.toFormattedNumber()
                    averageTime.text = "${it.average_time} min"
                    productType.text = it.type
                    productImage.bindImageUrl(
                        url = it.image,
                        placeholder = R.drawable.ic_baseline_rotate_left_24,
                        errorPlaceholder = R.drawable.ic_baseline_error_24
                    )
                    this.root.setOnClickListener { view ->
                        onClick(it)
                    }
                }
            }
        }
    }

    fun setData(list: List<Recipes>){
        this.completeItemList = list.toMutableList()
        submitList(list)
    }
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(query: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Recipes>()
                if (query.isNullOrBlank()) {
                    completeItemList?.let { filteredList.addAll(it) }
                } else {
                    for (item in completeItemList!!) {
                        if (item.name!!.contains(query, true)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults) {
                submitList(filterResults.values as MutableList<Recipes>)
            }
        }
    }
}
