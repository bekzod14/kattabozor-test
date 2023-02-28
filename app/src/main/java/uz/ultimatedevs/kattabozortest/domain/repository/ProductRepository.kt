package uz.ultimatedevs.kattabozortest.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.data.models.common.ResultData

interface ProductRepository {
    fun getProducts(): Flow<ResultData<List<ProductData>>>
}