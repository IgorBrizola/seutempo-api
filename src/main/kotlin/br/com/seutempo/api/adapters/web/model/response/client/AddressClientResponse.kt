package br.com.seutempo.api.adapters.web.model.response.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress

data class AddressClientResponse(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String?,
    val formatedAddress: String?,
    val additionalAddress: String? = null,
    val typeAddress: TypeAddress,
    val latitude: Double,
    val longitude: Double,
)
