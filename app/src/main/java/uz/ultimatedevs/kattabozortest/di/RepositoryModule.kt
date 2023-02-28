package uz.ultimatedevs.kattabozortest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.ultimatedevs.kattabozortest.domain.repository.ProductRepository
import uz.ultimatedevs.kattabozortest.domain.repository.impl.ProductRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

}