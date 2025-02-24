package br.com.seutempo.api.core.domain.exceptions

data class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
)
