package uz.ultimatedevs.kattabozortest.utils.extensions

import androidx.viewbinding.ViewBinding


fun ViewBinding.scope(block: ViewBinding.() -> Unit) {
    block.invoke(this)
}
