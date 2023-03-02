package uz.ultimatedevs.kattabozortest.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ultimatedevs.kattabozortest.R
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.databinding.ItemProductBinding


class ProductsAdapter :
    ListAdapter<ProductData, ProductsAdapter.LocationItemViewHolder>(LocationComparator) {


    inner class LocationItemViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            Glide.with(binding.root)
                .load(data.image.url)
                .placeholder(R.drawable.img_logo)
                .error(R.drawable.img_error)
                .into(binding.imgProduct)
            binding.txtCategory.text = data.category.split("/")[0].trim()
            binding.txtProductName.text = data.name
            binding.txtBrand.text =
                binding.root.context.getString(R.string.brand_formatter, data.brand)
            val sb = StringBuilder()
            data.attributes.forEach {
                sb.append(it.name.plus(": ").plus(it.value).plus(", "))
            }
            binding.txtCharacteristics.text =
                binding.root.context.getString(R.string.characteristics_formatter, sb.toString())
            binding.txtMerchant.text =
                binding.root.context.getString(R.string.merchant_formatter, data.merchant)
        }

    }

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) = holder.bind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LocationItemViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    companion object LocationComparator : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(
            oldItem: ProductData,
            newItem: ProductData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductData,
            newItem: ProductData
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.brand == newItem.brand
                    && oldItem.category == newItem.category
                    && oldItem.merchant == newItem.merchant
                    && oldItem.attributes == newItem.attributes
                    && oldItem.image == newItem.image
        }

    }
}