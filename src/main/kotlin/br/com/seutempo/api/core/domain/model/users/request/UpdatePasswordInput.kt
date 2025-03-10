package br.com.seutempo.api.core.domain.model.users.request

data class UpdatePasswordInput(
    val id: Int,
    val password: String,
)
