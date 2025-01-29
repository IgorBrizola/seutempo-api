package br.com.seutempo.api.exception.users.request

data class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
)
