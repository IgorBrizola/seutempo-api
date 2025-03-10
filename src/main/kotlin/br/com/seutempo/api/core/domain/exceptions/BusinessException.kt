package br.com.seutempo.api.core.domain.exceptions

open class BusinessException(
    message: String,
) : RuntimeException(message)
