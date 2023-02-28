package uz.ultimatedevs.kattabozortest.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.data.models.common.ResultData

interface GetProductsUseCase {
    fun execute(): Flow<ResultData<List<ProductData>>>
}