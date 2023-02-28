package uz.ultimatedevs.kattabozortest.domain.usecase.impl

import uz.ultimatedevs.kattabozortest.domain.repository.ProductRepository
import uz.ultimatedevs.kattabozortest.domain.usecase.GetProductsUseCase
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductsUseCase {
    override fun execute() = productRepository.getProducts()
}