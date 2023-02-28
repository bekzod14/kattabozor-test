package uz.ultimatedevs.kattabozortest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.ultimatedevs.kattabozortest.domain.usecase.GetProductsUseCase
import uz.ultimatedevs.kattabozortest.domain.usecase.impl.GetProductsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetProductsUseCase(impl: GetProductsUseCaseImpl): GetProductsUseCase

}