package uz.ultimatedevs.kattabozortest.ui.products

import kotlinx.coroutines.flow.Flow
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.data.models.common.ResultData

interface ProductsViewModel {
    val loadingFlow: Flow<Boolean>
    val errorFlow: Flow<String>
    val messageFlow: Flow<String>

    val productsListFlow: Flow<List<ProductData>>

    fun update()

}