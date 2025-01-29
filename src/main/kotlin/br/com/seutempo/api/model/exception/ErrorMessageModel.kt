package br.com.seutempo.api.model.exception

data class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
)
