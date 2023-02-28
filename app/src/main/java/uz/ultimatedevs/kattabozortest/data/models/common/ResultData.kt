package uz.ultimatedevs.kattabozortest.data.models.common

sealed interface ResultData<out T : Any> {

    object Loading : ResultData<Nothing>
    data class Success<out T : Any>(val data: T) : ResultData<T>
    data class Message(val message: String) : ResultData<Nothing>
    data class Error(
        val error: Throwable? = null,
        val messageString: String? = null
    ) : ResultData<Nothing>

}