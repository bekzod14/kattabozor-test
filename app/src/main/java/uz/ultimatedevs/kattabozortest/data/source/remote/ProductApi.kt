package uz.ultimatedevs.kattabozortest.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.ultimatedevs.kattabozortest.data.models.response.ProductsResponse

interface ProductApi {

    @GET("offers")
    suspend fun getProducts(): Response<ProductsResponse>

}