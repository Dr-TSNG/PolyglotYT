package icu.nullptr.polyglot.translate

class TranslationHttpException(
    val statusCode: Int,
    message: String,
) : IllegalStateException(message) {
    val isRetryable: Boolean
        get() = statusCode == 408 || statusCode == 429 || statusCode >= 500

    val isFatal: Boolean
        get() = statusCode == 401 || statusCode == 403 || statusCode == 404
}
