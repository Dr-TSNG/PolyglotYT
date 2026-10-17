package icu.nullptr.polyglot.translate

/**
 * The provider returned a response that does not match the requested batch,
 * for example a missing, duplicated or extra translation entry.
 */
class BatchTranslationException(message: String) : IllegalStateException(message)
