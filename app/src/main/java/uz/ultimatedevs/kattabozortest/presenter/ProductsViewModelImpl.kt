package uz.ultimatedevs.kattabozortest.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.ultimatedevs.kattabozortest.data.models.common.ProductData
import uz.ultimatedevs.kattabozortest.data.models.common.ResultData
import uz.ultimatedevs.kattabozortest.domain.usecase.GetProductsUseCase
import uz.ultimatedevs.kattabozortest.ui.products.ProductsViewModel
import uz.ultimatedevs.kattabozortest.utils.extensions.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModelImpl @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ProductsViewModel, ViewModel() {
    override val loadingFlow = eventValueFlow<Boolean>()
    override val errorFlow = eventValueFlow<String>()
    override val messageFlow = eventValueFlow<String>()
    override val productsListFlow = eventValueFlow<List<ProductData>>()

    override fun update() {
        viewModelScope.launch {
            getProductsUseCase.execute().collectLatest {
                when (it) {
                    ResultData.Loading -> {
                        loadingFlow.emit(true)
                    }
                    is ResultData.Success -> {
                        loadingFlow.emit(false)
                        productsListFlow.emit(it.data)
                    }
                    is ResultData.Error -> {
                        loadingFlow.emit(false)
                        it.error?.let {
                            errorFlow.emit(it.message.toString())
                        }
                        it.messageString?.let {
                            errorFlow.emit(it)
                        }
                    }
                    is ResultData.Message -> {
                        loadingFlow.emit(false)
                        it.message.let {
                            errorFlow.emit(it)
                        }
                    }
                }
            }
        }
    }

    init {
        update()
    }

}