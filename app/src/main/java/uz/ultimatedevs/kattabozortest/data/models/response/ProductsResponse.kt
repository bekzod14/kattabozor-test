package uz.ultimatedevs.kattabozortest.data.models.response

import uz.ultimatedevs.kattabozortest.data.models.common.ProductData

data class ProductsResponse(
    val offers: List<ProductData>
)
