package uz.ultimatedevs.kattabozortest.domain.repository.impl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.ultimatedevs.kattabozortest.R
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.data.models.common.ResultData
import uz.ultimatedevs.kattabozortest.data.source.remote.ProductApi
import uz.ultimatedevs.kattabozortest.domain.repository.ProductRepository
import uz.ultimatedevs.kattabozortest.utils.exception.NoInternetConnectionException
import uz.ultimatedevs.kattabozortest.utils.exception.ResponseBodyException
import uz.ultimatedevs.kattabozortest.utils.exception.ServerErrorException
import uz.ultimatedevs.kattabozortest.utils.exception.UnknownErrorException
import uz.ultimatedevs.kattabozortest.utils.extensions.hasInternetConnection
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    @ApplicationContext val context: Context
) : ProductRepository {
    override fun getProducts(): Flow<ResultData<List<ProductData>>> = flow {
        emit(ResultData.Loading)
        if (hasInternetConnection(context)) {
            val response = productApi.getProducts()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    emit(ResultData.Success(response.body()!!.offers))
                } else throw ResponseBodyException()
            } else if (response.code() in 500..599) {
                throw ServerErrorException()
            } else {
                val errorBody =
                    response.errorBody()?.string()
                if (errorBody != null) {
                    emit(ResultData.Error(messageString = errorBody))
                } else {
                    throw UnknownErrorException()
                }
            }
        } else throw NoInternetConnectionException()
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)
}