package uz.ultimatedevs.kattabozortest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.ultimatedevs.kattabozortest.data.source.remote.ProductApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @[Provides Singleton]
    fun getProductApi(apiClient: Retrofit): ProductApi =
        apiClient.create(ProductApi::class.java)

}