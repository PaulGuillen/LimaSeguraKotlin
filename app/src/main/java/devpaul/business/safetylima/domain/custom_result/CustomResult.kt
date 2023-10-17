package devpaul.business.safetylima.domain.custom_result

import devpaul.business.safetylima.domain.uitl.SingletonError

sealed class CustomResult<out T> {
    data class OnSuccess<out T>(val data: T) : CustomResult<T>()
    data class OnError<out T>(val error: CustomError) : CustomResult<T>()
}

open class CustomError(
    private val code: Int? = 0,
    val title: String? = null,
    private val subtitle: String? = null,
    val cause: Throwable? = null,
) {
    private val generalMessage = "En este momento el servicio no está disponible"

    init {
        if (code == 400 || code == 401 || code == 404 || code == 503 || code == 504
            || code == 502 || code == 408
        ) {
            SingletonError.subTitle = subtitle?.let {
                it.ifEmpty {
                    generalMessage
                }
            } ?: generalMessage
        }
        if (code == 500) {
            SingletonError.subTitle = subtitle?.let {
                it.ifEmpty {
                    generalMessage
                }
            } ?: generalMessage
        }
    }

    override fun toString(): String {
        return subtitle ?: ""
    }
}

class HttpError(
    code: Int? = 0,
    title: String? = null,
    subtitle: String? = null,
) : CustomError(code, title) {
    init {
        when (code) {
            400, 401, 502, 404, 503, 500, 408 -> {
                SingletonError.title = title
                SingletonError.subTitle = subtitle
                SingletonError.code = code
            }

            else -> {
                SingletonError.title = title
                SingletonError.subTitle = "Not Maped"
                SingletonError.code = 0
            }
        }
    }
}

class CustomNotFoundError(code: Int? = 0, title: String? = null, subTitle: String? = null) :
    CustomError(code, subTitle ?: "Data not found", title)

