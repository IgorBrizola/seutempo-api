package br.com.seutempo.api.core.domain.model.client.request

data class UpdateClient(
    val id: Int,
    val surname: String?,
    val photoUser: String?,
    val phone: String?,
)
