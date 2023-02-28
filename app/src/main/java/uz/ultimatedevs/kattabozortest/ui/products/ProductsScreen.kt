package uz.ultimatedevs.kattabozortest.ui.products

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.ultimatedevs.kattabozortest.R
import uz.ultimatedevs.kattabozortest.databinding.ScreenProductsBinding
import uz.ultimatedevs.kattabozortest.presenter.ProductsViewModelImpl
import uz.ultimatedevs.kattabozortest.utils.extensions.scope
import uz.ultimatedevs.kattabozortest.utils.extensions.visibleOrGone

@AndroidEntryPoint
class ProductsScreen : Fragment(R.layout.screen_products) {

    private val viewBinding: ScreenProductsBinding by viewBinding(ScreenProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModels<ProductsViewModelImpl>()
    private val adapter: ProductsAdapter by lazy { ProductsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEventObservers()
    }

    private fun setEventObservers() {
        viewModel.errorFlow.onEach {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.error)
                .setMessage(it)
                .setNeutralButton(R.string.retry) { dialog, _ ->
                    viewModel.update()
                    dialog.dismiss()
                }.show()
        }.launchIn(lifecycleScope)
        viewModel.messageFlow.onEach {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.message)
                .setMessage(it)
                .setNeutralButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        setUiObservers()
    }

    private fun setUiObservers() {
        viewModel.loadingFlow.onEach {
            viewBinding.progress.visibleOrGone(it)
            viewBinding.swipeRefresh.isRefreshing = false
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.productsListFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initViews() {
        viewBinding.listProducts.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.listProducts.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            RecyclerView.VERTICAL
        )
        viewBinding.listProducts.addItemDecoration(dividerItemDecoration)
        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.update()
        }
    }
}